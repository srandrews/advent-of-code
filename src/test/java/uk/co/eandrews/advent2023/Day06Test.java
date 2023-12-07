package uk.co.eandrews.advent2023;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2023.day.day06.Day6;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day06Test {
    String input = """
            Time:      7  15   30
            Distance:  9  40  200
            """;

    private final InputParser<Stream<String>> parser = inputResolver -> input.lines();

    @Test
    void partOneSolution_should_return_288() {
        final Day6 day6 = new Day6(parser);
        assertThat(day6.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(288);
    }

    @Test
    void partTwoSolution_should_return_71503() {
        final Day6 day6 = new Day6(parser);
        assertThat(day6.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(71503);
    }
}
