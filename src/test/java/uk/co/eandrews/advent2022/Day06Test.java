package uk.co.eandrews.advent2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.co.eandrews.advent2022.day.day06.Day6;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day06Test {

    @ParameterizedTest
    @CsvSource({"bvwbjplbgvbhsrlpgdmjqwftvncz,5", "nppdvjthqldpwncqszvftbrmjlhg,6",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,10", "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,11"})
    void partOneSolution(String input, int expected) {
        final InputParser<Stream<String>> parser = inputResolver -> input.lines();
        final Day6 day6 = new Day6(parser);
        assertThat(day6.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"mjqjpqmgbljsphdztnvjfqwrcgsmlb,19", "bvwbjplbgvbhsrlpgdmjqwftvncz,23",
            "nppdvjthqldpwncqszvftbrmjlhg,23", "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,29",
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw, 26"})
    void partTwoSolution_test(String input, int expected) {
        final InputParser<Stream<String>> parser = inputResolver -> input.lines();

        final Day6 day6 = new Day6(parser);
        assertThat(day6.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(expected);
    }

}
