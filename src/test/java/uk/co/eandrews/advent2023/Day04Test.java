package uk.co.eandrews.advent2023;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2023.day.day04.Day4;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day04Test {
    String input = """
              Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
              Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
              Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
              Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
              Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
              Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
              """;

    private final InputParser<Stream<String>> parser = inputResolver -> input.lines();

    @Test
    void partOneSolution_should_return_13() {
        final Day4 day4 = new Day4(parser);
        assertThat(day4.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(13);
    }

    @Test
    void partTwoSolution_should_return_30() {
        final Day4 day4 = new Day4(parser);
        assertThat(day4.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(30);
    }

}
