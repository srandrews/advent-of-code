package uk.co.eandrews.day.day2;

import lombok.experimental.ExtensionMethod;
import one.util.streamex.LongStreamEx;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;
import uk.co.eandrews.day.Day;
import uk.co.eandrews.day.PuzzleSolution;
import uk.co.eandrews.util.LongStreamExUtil;
import uk.co.eandrews.util.StreamExUtil;
import uk.co.eandrews.util.Vector2;
import uk.co.eandrews.util.Vector3;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Collection;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Component
public class Day2 extends Day<Stream<Command>, Long> {

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
            return result.x() * result.y();
        };
    }

    public PuzzleSolution<Stream<Command>, Long> solution(final Vector3 seed) {
        return commands -> {
            final var result = StreamEx.of(commands).foldLeft(seed, (position, command) -> command.apply(position));
            return result.x() * result.y();
        };
    }



}
