package uk.co.eandrews.advent2022.day.day16;

import lombok.experimental.ExtensionMethod;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.StreamExUtil;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Component("Day16-2022")
@ExtensionMethod({StreamEx.class, StreamExUtil.class})
public class Day16 extends Day2022<Stream<String>, Long> {

    public Day16(final InputParser<Stream<String>> inputParser) {
        super(1, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Stream<String>, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Stream<String>, Long> partOneSolution() {
        return input -> 0L;
    }

    public PuzzleSolution<Stream<String>, Long> partTwoSolution() {
        return input ->  0L;
    }
}
