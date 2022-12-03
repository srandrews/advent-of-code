package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day2.Day2;
import uk.co.eandrews.advent2022.day.day2.RockPaperScissorRound;
import uk.co.eandrews.advent2022.day.day2.RockPaperScissorsInputParser;
import uk.co.eandrews.advent2022.day.day3.BackPack;
import uk.co.eandrews.advent2022.day.day3.BackPackInputParser;
import uk.co.eandrews.advent2022.day.day3.Day3;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DayThreeTest {

    String input = """
        vJrwpWtwJgWrhcsFMMfFFhFp
        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        PmmdzqPrVvPwwTWBwg
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
        """;

    private final InputParser<Stream<BackPack>> parser = new BackPackInputParser();

    @Test
    void partOneSolution_should_return_157() {
        final Day3 day3 = new Day3(parser);
        assertThat(day3.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(157);
    }

    @Test
    void partTwoSolution_should_return_70() {
        final Day3 day3 = new Day3(parser);
        assertThat(day3.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(70);
    }

}
