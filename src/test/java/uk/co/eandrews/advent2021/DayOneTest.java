package uk.co.eandrews.advent2021;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2021.day1.Day1;
import uk.co.eandrews.util.io.input.parser.InputParser;
import uk.co.eandrews.util.io.input.resolver.InputResolver;

import java.util.List;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

class DayOneTest {

    private List<String> exampleInput  = List.of("199", "200", "208", "210", "200", "207", "240", "269", "260", "263");

    private final InputParser<LongStream> parser = input -> exampleInput.stream().mapToLong(Long::parseLong);

    private final InputResolver dummyInputResolver = day -> exampleInput.stream().map(String::valueOf);

    @Test
    void partOneSolution_should_return_7() {
        final Day1 day1 = new Day1(parser);
        assertThat(day1.partOneSolution().solve(exampleInput.stream().mapToLong(Long::parseLong))).isEqualTo(7);
    }

    @Test
    void partTwoSolution_should_return_5() {
        final Day1 day1 = new Day1(parser);
        assertThat(day1.partTwoSolution().solve(exampleInput.stream().mapToLong(Long::parseLong))).isEqualTo(5);
    }

    @Test
    void solve_should_equal_7And5() {
        final Day1 day1 = new Day1(parser);
        assertThat(day1.solve(dummyInputResolver)).isEqualTo(List.of(7L, 5L));
    }

}