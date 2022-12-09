package uk.co.eandrews.advent2022.day.day09;

import lombok.experimental.ExtensionMethod;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.StreamExUtil;
import uk.co.eandrews.util.Vector2;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Component("Day9-2022")
@ExtensionMethod({StreamEx.class, StreamExUtil.class})
public class Day9 extends Day2022<Stream<Command>, Long> {

    public Day9(final InputParser<Stream<Command>> inputParser) {
        super(9, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Stream<Command>, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Stream<Command>, Long> partOneSolution() {
        RopeGrid ropeGrid = new RopeGrid(2);
        Set<Vector2> tailVisited = new HashSet<>();
        return input -> {
            input.forEach(command -> command.apply(ropeGrid, tailVisited));
            return Long.valueOf(tailVisited.size());
        };
    }

    public PuzzleSolution<Stream<Command>, Long> partTwoSolution() {
        RopeGrid ropeGrid = new RopeGrid(10);
        Set<Vector2> tailVisited = new HashSet<>();
        return input -> {
            input.forEach(command -> command.apply(ropeGrid, tailVisited));
            return Long.valueOf(tailVisited.size());
        };
    }
}