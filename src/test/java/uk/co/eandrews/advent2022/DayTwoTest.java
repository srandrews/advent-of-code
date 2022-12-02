package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day2.Day2;
import uk.co.eandrews.advent2022.day.day2.RockPaperScissorRound;
import uk.co.eandrews.advent2022.day.day2.RockPaperScissorsInputParser;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DayTwoTest {

    String input = """
        A Y
        B X
        C Z
        """;

    private final InputParser<Stream<RockPaperScissorRound>> parser = new RockPaperScissorsInputParser();

    @Test
    void partOneSolution_should_return_15() {
        final Day2 day2 = new Day2(parser);
        assertThat(day2.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(15);
    }

    @Test
    void partTwoSolution_should_return_12() {
        final Day2 day2 = new Day2(parser);
        assertThat(day2.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(12);
    }

}
