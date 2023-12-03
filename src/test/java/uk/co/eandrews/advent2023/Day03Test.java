package uk.co.eandrews.advent2023;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2023.day.day02.Day2;
import uk.co.eandrews.advent2023.day.day02.Game;
import uk.co.eandrews.advent2023.day.day02.GameParser;
import uk.co.eandrews.advent2023.day.day03.Day3;
import uk.co.eandrews.advent2023.day.day03.Schematic;
import uk.co.eandrews.advent2023.day.day03.SchematicParser;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day03Test {

    String input = """
              467..114..
              ...*......
              ..35..633.
              ......#...
              617*......
              .....+.58.
              ..592.....
              ......755.
              ...$.*....
              .664.598..
              """;

    private final InputParser<List<Schematic>> parser = new SchematicParser();

    @Test
    void partOneSolution_should_return_4361() {
        final Day3 day3 = new Day3(parser);
        assertThat(day3.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(4361);
    }

    @Test
    void partTwoSolution_should_return_467835() {
        final Day3 day3 = new Day3(parser);
        assertThat(day3.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(467835);
    }

}
