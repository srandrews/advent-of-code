package uk.co.eandrews.advent2021;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2021.day.day3.Day3;
import uk.co.eandrews.util.io.input.parser.InputParser;
import uk.co.eandrews.util.io.input.resolver.InputResolver;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day3Test {
    private final List<String> exampleInput = List.of(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    );

    private final InputParser<Stream<String>> dummyParser = input -> exampleInput.stream();

    private final InputResolver dummyInputResolver = day -> exampleInput.stream();

    @Test
    void partOneSolution_should_return_198() {
        final Day3 day3 = new Day3(dummyParser);
        assertThat(day3.getSolutions().get(0).solve(exampleInput.stream())).isEqualTo(198);
    }

//    @Test
//    void partTwoSolution_should_return_230() {
//        final Day3 day3 = new Day3(dummyParser);
//        assertEquals(230, day3.getSolutions().get(1).solve(exampleInput.stream()));
//    }
//
//    @Test
//    void solve_should_equal_198And230() {
//        final Day3 day3 = new Day3(dummyParser);
//        assertEquals(List.of(198L, 230L), day3.solve(dummyInputResolver));
//    }
}
