package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day15.Day15;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day15Test {

    String input = """
        Sensor at x=2, y=18: closest beacon is at x=-2, y=15
        Sensor at x=9, y=16: closest beacon is at x=10, y=16
        Sensor at x=13, y=2: closest beacon is at x=15, y=3
        Sensor at x=12, y=14: closest beacon is at x=10, y=16
        Sensor at x=10, y=20: closest beacon is at x=10, y=16
        Sensor at x=14, y=17: closest beacon is at x=10, y=16
        Sensor at x=8, y=7: closest beacon is at x=2, y=10
        Sensor at x=2, y=0: closest beacon is at x=2, y=10
        Sensor at x=0, y=11: closest beacon is at x=2, y=10
        Sensor at x=20, y=14: closest beacon is at x=25, y=17
        Sensor at x=17, y=20: closest beacon is at x=21, y=22
        Sensor at x=16, y=7: closest beacon is at x=15, y=3
        Sensor at x=14, y=3: closest beacon is at x=15, y=3
        Sensor at x=20, y=1: closest beacon is at x=15, y=3
        """;

    private final InputParser<Stream<String>> parser = inputResolver -> input.lines();

    @Test
    void partOneSolution_should_return_26() {
        final Day15 day15 = new Day15(parser);
        day15.setRow(10);
        assertThat(day15.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(26);
    }

    @Test
    void partTwoSolution_should_return_56000011() {
        final Day15 day15 = new Day15(parser);
        day15.setRow(10);
        day15.setMax(20);
        day15.setMin(0);
        assertThat(day15.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(56000011L);
    }

}
