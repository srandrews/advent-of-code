package uk.co.eandrews.advent2023;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2023.day.day05.Almanac;
import uk.co.eandrews.advent2023.day.day05.AlmanacParser;
import uk.co.eandrews.advent2023.day.day05.Day5;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day05Test {
    String input = """
              seeds: 79 14 55 13
                      
              seed-to-soil map:
              50 98 2
              52 50 48
              
              soil-to-fertilizer map:
              0 15 37
              37 52 2
              39 0 15
              
              fertilizer-to-water map:
              49 53 8
              0 11 42
              42 0 7
              57 7 4
              
              water-to-light map:
              88 18 7
              18 25 70
              
              light-to-temperature map:
              45 77 23
              81 45 19
              68 64 13
              
              temperature-to-humidity map:
              0 69 1
              1 0 69
              
              humidity-to-location map:
              60 56 37
              56 93 4
              """;

    private final InputParser<Almanac> parser = new AlmanacParser();

    @Test
    void partOneSolution_should_return_35() {
        final Day5 day5 = new Day5(parser);
        assertThat(day5.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(35);
    }

    @Test
    void partTwoSolution_should_return_46() {
        final Day5 day5 = new Day5(parser);
        assertThat(day5.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(46);
    }
}
