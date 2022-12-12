package uk.co.eandrews.advent2022.day.day12;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.Vector2;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component("Day12-2022")
@Slf4j
public class Day12 extends Day2022<char[][], Long> {

    public Day12(final InputParser<char[][]> inputParser) {
        super(12, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<char[][], Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<char[][], Long> partOneSolution() {
        return input -> {
            Vector2 start = findPoint(input,'S').get(0);
            Vector2 end = findPoint(input,'E').get(0);
            return getPathLength(input, start, end);
        };
    }

    public PuzzleSolution<char[][], Long> partTwoSolution() {
        return input -> {
            Vector2 end = findPoint(input, 'E').get(0);

            return findPoint(input, 'a').stream()
                .map(start -> getPathLength(input, start, end))
                .mapToLong(value -> value)
                .min().orElse(0L);

        };
    }

    private long getPathLength(char[][] topoMap, Vector2 start, Vector2 end) {
        Graph graph = getGraph(topoMap);
        Node startNode = graph.getNodes().stream().filter(node -> node.getVector().equals(start)).findFirst().get();

        Dijkstra.calculateShortestPathFromSource(graph, startNode);

        Node endNode = graph.getNodes().stream().filter(node -> node.getVector().equals(end)).findFirst().get();
        return endNode.getDistance();
    }


    private Graph getGraph(char[][] topoMap) {

        Graph graph = new Graph();

        Map<Vector2, Node> nodes = IntStream.range(0, topoMap[0].length)
            .mapToObj(x -> IntStream.range(0, topoMap.length)
                .mapToObj(y -> Vector2.builder().x(x).y(y).build())
                .map(Node::new))
            .flatMap(nodeStream -> nodeStream)
            .collect(Collectors.toMap(Node::getVector, Function.identity()));

        nodes.values()
            .forEach(node -> getPossibleDirectionsToMove(topoMap, node.getVector())
                .forEach(vector2 -> node.addDestination(nodes.get(vector2),1)));

        nodes.values().forEach(graph::addNode);

        return graph;
    }

    private List<Vector2> getPossibleDirectionsToMove(char[][] topoMap, Vector2 currentPosition) {
        return Stream.of(
                currentPosition.up(1),
                currentPosition.down(1),
                currentPosition.left(1),
                currentPosition.right(1))
            .filter(newPosition -> newPosition.y()>=0)
            .filter(newPosition -> newPosition.x()>=0)
            .filter(newPosition -> newPosition.y()<topoMap.length)
            .filter(newPosition -> newPosition.x()<topoMap[0].length)
            .filter(newPosition -> canMoveTo(topoMap, currentPosition, newPosition))
            .toList();
    }

    private char getPositionDetails(char[][] topoMap, Vector2 position) {
        return topoMap[(int)position.y()][(int)position.x()];
    }

    private boolean canMoveTo(char[][] topoMap, Vector2 currentPosition, Vector2 newPosition) {
        char current = getPositionDetails(topoMap, currentPosition);
        char next = getPositionDetails(topoMap, newPosition);

        if (current=='S') {
            current='a';
        }

        if (next=='E') {
            next='z';
        }

        return current+1>=next;
    }

    private List<Vector2> findPoint(char[][] topoMap, char value) {
        return IntStream.range(0, topoMap[0].length)
            .mapToObj(x -> IntStream.range(0, topoMap.length)
                .filter(y -> topoMap[y][x]==value)
                .mapToObj(y -> Vector2.builder().x(x).y(y).build()))
            .flatMap(vector2Stream -> vector2Stream)
            .toList();
    }


}