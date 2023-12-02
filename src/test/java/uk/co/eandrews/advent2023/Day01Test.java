package uk.co.eandrews.advent2023;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2023.day.day01.Day1;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day01Test {

    String input1 = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
        """;

    String input2 = """
            two1nine
            eighthree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
            """;

    private final InputParser<Stream<String>> parser1 = inputResolver -> input1.lines();
    private final InputParser<Stream<String>> parser2 = inputResolver -> input2.lines();

    @Test
    void partOneSolution_should_return_142() {
        final Day1 day1 = new Day1(parser1);
        assertThat(day1.partOneSolution().solve(input1.lines())).isEqualTo(142);
    }

    @Test
    void partTwoSolution_should_return_281() {
        final Day1 day1 = new Day1(parser2);
        assertThat(day1.partTwoSolution().solve(input2.lines())).isEqualTo(281);
    }

}
