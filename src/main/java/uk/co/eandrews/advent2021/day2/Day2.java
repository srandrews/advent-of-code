package uk.co.eandrews.advent2021.day2;

import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2021.Day2021;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.Vector2;
import uk.co.eandrews.util.Vector3;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Component
public class Day2 extends Day2021<Stream<Command>, Long> {

    public Day2(final InputParser<Stream<Command>> inputParser) {
        super(2, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Stream<Command>, Long>> getSolutions() {
        return List.of(
            input -> solution(Vector2.ZERO).solve(input),
            input -> solution(Vector3.ZERO).solve(input)
        );
    }

    public PuzzleSolution<Stream<Command>, Long> solution(final Vector2 seed) {
        return commands -> {
            final var result = StreamEx.of(commands).foldLeft(seed, (position, command) -> command.apply(position));
            return (long)result.x() * result.y();
        };
    }

    public PuzzleSolution<Stream<Command>, Long> solution(final Vector3 seed) {
        return commands -> {
            final var result = StreamEx.of(commands).foldLeft(seed, (position, command) -> command.apply(position));
            return result.x() * result.y();
        };
    }



}
