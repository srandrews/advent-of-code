package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day04.Day4;
import uk.co.eandrews.advent2022.day.day04.SectionAssignment;
import uk.co.eandrews.advent2022.day.day04.SectionAssignmentInputParser;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day04Test {

    String input = """
        2-4,6-8
        2-3,4-5
        5-7,7-9
        2-8,3-7
        6-6,4-6
        2-6,4-8
        """;

    private final InputParser<Stream<SectionAssignment>> parser = new SectionAssignmentInputParser();

    @Test
    void partOneSolution_should_return_2() {
        final Day4 day4 = new Day4(parser);
        assertThat(day4.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(2);
    }

    @Test
    void partTwoSolution_should_return_4() {
        final Day4 day4 = new Day4(parser);
        assertThat(day4.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(4);
    }

}
