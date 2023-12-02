package uk.co.eandrews.advent2023;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day09.Command;
import uk.co.eandrews.advent2022.day.day09.CommandParser;
import uk.co.eandrews.advent2023.day.day01.Day1;
import uk.co.eandrews.advent2023.day.day02.Day2;
import uk.co.eandrews.advent2023.day.day02.Game;
import uk.co.eandrews.advent2023.day.day02.GameParser;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day02Test {

    String input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
            """;

    private final InputParser<Stream<Game>> parser = new GameParser();

    @Test
    void partOneSolution_should_return_8() {
        final Day2 day2 = new Day2(parser);
        assertThat(day2.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(8);
    }

    @Test
    void partTwoSolution_should_return_2286() {
        final Day2 day2 = new Day2(parser);
        assertThat(day2.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(2286);
    }

}
