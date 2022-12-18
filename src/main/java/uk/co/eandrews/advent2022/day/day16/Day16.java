package uk.co.eandrews.advent2022.day.day16;

import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.dijkstra.Dijkstra;
import uk.co.eandrews.util.dijkstra.Graph;
import uk.co.eandrews.util.dijkstra.Node;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("Day16-2022")
public class Day16 extends Day2022<Graph<Valve>, Long> {

    public Day16(final InputParser<Graph<Valve>> inputParser) {
        super(16, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Graph<Valve>, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Graph<Valve>, Long> partOneSolution() {
        return input -> getAllRoutes(input, 30).stream()
            .mapToLong(value ->  getPressureReleased(value, 30))
            .max().orElse(0L);
    }

    public PuzzleSolution<Graph<Valve>, Long> partTwoSolution() {
        return input -> {
            Set<ValveOpeningTimes> allRoutes = getAllRoutes(input, 26);

            List<MeAndElephant> meAndElephantList = allRoutes.stream()
                .flatMap(valveOpeningTimes -> allRoutes.stream()
                        .filter(vot -> {
                           Set<Valve> valves = new HashSet<>(vot.getValveTimes().keySet());
                           int size =valves.size();
                           valves.removeAll(valveOpeningTimes.getValveTimes().keySet());
                           return valves.size()==size;
                        }).map(vot -> new MeAndElephant(valveOpeningTimes, vot)))
                .toList();
            return 0L;
        };
    }

    private Set<ValveOpeningTimes> getAllRoutes(Graph<Valve> valves, int minutes) {

        Node<Valve> startNode = valves.getNodes().stream()
            .filter(valveNode -> valveNode.getId().name().equals("AA")).findFirst().orElseThrow();

        Set<Node<Valve>> stillToOpen = valves.getNodes()
            .stream()
            .filter(valveNode -> valveNode.getId().flowRate()!=0)
            .collect(Collectors.toSet());

        List<Distance<Valve>> distances = new ArrayList<>();

        Stream.concat(Stream.of(startNode), stillToOpen.stream())
            .forEach(node -> {
                valves.getNodes().forEach(valveNode -> valveNode.setDistance(Integer.MAX_VALUE));
                Dijkstra.calculateShortestPathFromSource(node);
                stillToOpen.forEach(valveNode -> distances.add(Distance.<Valve>builder()
                    .distance(valveNode.getDistance())
                    .startNode(node)
                    .endNode(valveNode)
                    .build()));
            });

        return getValveOpenedTimes(distances, 0, stillToOpen,
                ValveOpeningTimes.builder().valveTimes(new HashMap<>()).build(), startNode, startNode, minutes);

    }

    private long getPressureReleased(ValveOpeningTimes valveOpeningTimes, int maximumTime) {

        return valveOpeningTimes.getValveTimes().entrySet().stream()
            .mapToLong(valveIntegerEntry -> {
                Valve v = valveIntegerEntry.getKey();
                return (long) (maximumTime - valveIntegerEntry.getValue()) *v.flowRate();
            }).sum();
    }

    private Set<ValveOpeningTimes> getValveOpenedTimes(List<Distance<Valve>> distances, int currentTime,
                                                    Set<Node<Valve>> stillToOpen, ValveOpeningTimes valveOpenedTimes,
                                                    Node<Valve> currentValve, Node<Valve> moveTo, int maxTime) {

        Set<ValveOpeningTimes> valveOpeningTimes = new HashSet<>();

        valveOpenedTimes = valveOpenedTimes.toBuilder().
            valveTimes(new HashMap<>(valveOpenedTimes.getValveTimes())).build();

        if (!currentValve.equals(moveTo)) {
            int time = distances.stream()
                .filter(valveDistance -> valveDistance.getStartNode().equals(currentValve))
                .filter(valveDistance -> valveDistance.getEndNode().equals(moveTo))
                .findFirst().orElseThrow().getDistance();
            currentTime = currentTime + time + 1;

            if (currentTime>=maxTime) {
                valveOpeningTimes.add(valveOpenedTimes);
                return valveOpeningTimes;
            }

            stillToOpen = new HashSet<>(stillToOpen);
            stillToOpen.remove(moveTo);

            valveOpenedTimes.getValveTimes().put(moveTo.getId(), currentTime);

        }

        if (stillToOpen.isEmpty()) {
            valveOpeningTimes.add(valveOpenedTimes);
        }

        for (Node<Valve> valveNode : stillToOpen) {
            Set<ValveOpeningTimes> a = getValveOpenedTimes(distances, currentTime, stillToOpen,
                valveOpenedTimes, moveTo, valveNode, maxTime);
            valveOpeningTimes.addAll(a);
        }

        return valveOpeningTimes;
    }


}
