package uk.co.eandrews.advent2022.day.day01;

import lombok.experimental.ExtensionMethod;
import one.util.streamex.LongStreamEx;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.StreamExUtil;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Component("Day1-2022")
@ExtensionMethod({StreamEx.class, StreamExUtil.class})
public class Day1 extends Day2022<Stream<String>, Long> {

    public Day1(final InputParser<Stream<String>> inputParser) {
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
        return input -> getSummedStream(input)
                .max(Long::compareTo).orElse(0L);
    }

    public PuzzleSolution<Stream<String>, Long> partTwoSolution() {
        return input ->  getSummedStream(input)
            .sorted(Comparator.reverseOrder())
            .limit(3)
            .mapToLong(value -> value)
            .sum();
    }

    private StreamEx<Long> getSummedStream(Stream<String> input) {
        return input.toList().ofVariableSubLists(String::isEmpty)
            .map(strings -> strings.stream().map(Long::parseLong).toList())
            .map(LongStreamEx::of)
            .map(LongStreamEx::sum);
    }
}
