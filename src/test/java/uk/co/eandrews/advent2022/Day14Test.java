package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day14.Day14;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day14Test {

    String input = """
        498,4 -> 498,6 -> 496,6
        503,4 -> 502,4 -> 502,9 -> 494,9
        """;

    private final InputParser<Stream<String>> parser = inputResolver -> input.lines();

    @Test
    void partOneSolution_should_return_24() {
        final Day14 day14 = new Day14(parser);
        assertThat(day14.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(24);
    }

    @Test
    void partTwoSolution_should_return_93() {
        final Day14 day14 = new Day14(parser);
        assertThat(day14.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(93L);
    }

}
