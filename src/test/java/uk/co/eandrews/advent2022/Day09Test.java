package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day09.Command;
import uk.co.eandrews.advent2022.day.day09.CommandParser;
import uk.co.eandrews.advent2022.day.day09.Day9;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day09Test {

    private final InputParser<Stream<Command>> parser = new CommandParser();

    @Test
    void partOneSolution_should_return_13() {

        String input = """
                   R 4
                   U 4
                   L 3
                   D 1
                   R 4
                   D 1
                   L 5
                   R 2
                   """;

        final Day9 day9 = new Day9(parser);
        assertThat(day9.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(13);
    }

    @Test
    void partTwoSolution_should_return_1() {
        String input = """
                   R 4
                   U 4
                   L 3
                   D 1
                   R 4
                   D 1
                   L 5
                   R 2
                   """;

        final Day9 day9 = new Day9(parser);
        assertThat(day9.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(1);
    }

    @Test
    void partTwoSolution_should_return_36() {
        String input = """
                R 5
                U 8
                L 8
                D 3
                R 17
                D 10
                L 25
                U 20
                """;

        final Day9 day9 = new Day9(parser);
        assertThat(day9.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(36);
    }

}
