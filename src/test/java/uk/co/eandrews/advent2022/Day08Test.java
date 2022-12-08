package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;

import uk.co.eandrews.advent2022.day.day07.Day7;
import uk.co.eandrews.advent2022.day.day07.Directory;
import uk.co.eandrews.advent2022.day.day07.DirectoryOperationsInputParser;
import uk.co.eandrews.advent2022.day.day08.Day8;
import uk.co.eandrews.advent2022.day.day08.TreeInputParser;
import uk.co.eandrews.util.io.input.parser.InputParser;

import static org.assertj.core.api.Assertions.assertThat;

class Day08Test {

    String input = """
                   30373
                   25512
                   65332
                   33549
                   35390
                   """;

    private final InputParser<Integer[][]> parser = new TreeInputParser();

    @Test
    void partOneSolution_should_return_21() {
        final Day8 day8 = new Day8(parser);
        assertThat(day8.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(21);
    }
//
//    @Test
//    void partTwoSolution_should_return_24933642() {
//        final Day7 day7 = new Day7(parser);
//        assertThat(day7.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(24933642);
//    }

}
