package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day01.Day1;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day01Test {

    String input = """
        1000
        2000
        3000
                
        4000
                
        5000
        6000
                
        7000
        8000
        9000
                
        10000
        """;

    private final InputParser<Stream<String>> parser = inputResolver -> input.lines();

    @Test
    void partOneSolution_should_return_24_000() {
        final Day1 day1 = new Day1(parser);
        assertThat(day1.partOneSolution().solve(input.lines())).isEqualTo(24_000);
    }

    @Test
    void partTwoSolution_should_return_45_000() {
        final Day1 day1 = new Day1(parser);
        assertThat(day1.partTwoSolution().solve(input.lines())).isEqualTo(45_000);
    }

}
