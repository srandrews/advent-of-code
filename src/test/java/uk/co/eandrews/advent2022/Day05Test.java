package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day05.CraneOperation;
import uk.co.eandrews.advent2022.day.day05.CraneOperationInputParser;
import uk.co.eandrews.advent2022.day.day05.Day5;
import uk.co.eandrews.util.io.input.parser.InputParser;

import static org.assertj.core.api.Assertions.assertThat;

class Day05Test {
    String input = """
            [D]   \s
        [N] [C]   \s
        [Z] [M] [P]
         1   2   3\s
                
        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2
        """;

    private final InputParser<CraneOperation> parser = new CraneOperationInputParser();

    @Test
    void partOneSolution_should_return_CMZ() {
        final Day5 day5 = new Day5(parser);
        assertThat(day5.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo("CMZ");
    }

    @Test
    void partTwoSolution_should_return_MCD() {
        final Day5 day5 = new Day5(parser);
        assertThat(day5.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo("MCD");
    }

}
