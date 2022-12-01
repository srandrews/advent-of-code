package uk.co.eandrews.advent2021.day.day1;

import lombok.experimental.ExtensionMethod;
import one.util.streamex.LongStreamEx;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2021.day.Day;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.LongStreamExUtil;
import uk.co.eandrews.util.StreamExUtil;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Collection;
import java.util.List;
import java.util.stream.LongStream;

@Component
@ExtensionMethod({StreamEx.class, StreamExUtil.class, LongStreamEx.class, LongStreamExUtil.class})
public class Day1 extends Day<LongStream, Long> {

    public Day1(final InputParser<LongStream> inputParser) {
        super(1, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<LongStream, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<LongStream, Long> partOneSolution() {
        return input -> LongStreamEx.of(input).pairMap((a, b) -> b > a ? 1 : 0).sum();
    }

    public PuzzleSolution<LongStream, Long> partTwoSolution() {
        return input -> partOneSolution().solve(StreamEx.of(input.boxed().toList()).windowed(3)
            .map(i -> i.sum()).mapToLong(i -> i));
    }
}
