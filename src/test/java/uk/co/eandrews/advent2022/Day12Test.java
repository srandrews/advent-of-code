package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day12.Day12;
import uk.co.eandrews.util.io.input.parser.InputParser;

import static org.assertj.core.api.Assertions.assertThat;

class Day12Test {

    String input = """
        Sabqponm
        abcryxxl
        accszExk
        acctuvwj
        abdefghi
        """;

    private final InputParser<char[][]> parser = inputResolver ->
        input.lines().map(String::toCharArray).toArray(char[][]::new);

    @Test
    void partOneSolution_should_return_31() {
        final Day12 day12 = new Day12(parser);
        assertThat(day12.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(31);
    }

    @Test
    void partTwoSolution_should_return_29() {
        final Day12 day12 = new Day12(parser);
        assertThat(day12.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(29L);
    }

}
