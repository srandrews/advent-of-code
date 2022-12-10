package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day10.Cycle;
import uk.co.eandrews.advent2022.day.day10.Day10;
import uk.co.eandrews.advent2022.day.day10.Instruction;
import uk.co.eandrews.advent2022.day.day10.InstructionParser;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day10Test {

    String input = """
           addx 15
           addx -11
           addx 6
           addx -3
           addx 5
           addx -1
           addx -8
           addx 13
           addx 4
           noop
           addx -1
           addx 5
           addx -1
           addx 5
           addx -1
           addx 5
           addx -1
           addx 5
           addx -1
           addx -35
           addx 1
           addx 24
           addx -19
           addx 1
           addx 16
           addx -11
           noop
           noop
           addx 21
           addx -15
           noop
           noop
           addx -3
           addx 9
           addx 1
           addx -3
           addx 8
           addx 1
           addx 5
           noop
           noop
           noop
           noop
           noop
           addx -36
           noop
           addx 1
           addx 7
           noop
           noop
           noop
           addx 2
           addx 6
           noop
           noop
           noop
           noop
           noop
           addx 1
           noop
           noop
           addx 7
           addx 1
           noop
           addx -13
           addx 13
           addx 7
           noop
           addx 1
           addx -33
           noop
           noop
           noop
           addx 2
           noop
           noop
           noop
           addx 8
           noop
           addx -1
           addx 2
           addx 1
           noop
           addx 17
           addx -9
           addx 1
           addx 1
           addx -3
           addx 11
           noop
           noop
           addx 1
           noop
           addx 1
           noop
           noop
           addx -13
           addx -19
           addx 1
           addx 3
           addx 26
           addx -30
           addx 12
           addx -1
           addx 3
           addx 1
           noop
           noop
           noop
           addx -9
           addx 18
           addx 1
           addx 2
           noop
           noop
           addx 9
           noop
           noop
           noop
           addx -1
           addx 2
           addx -37
           addx 1
           addx 3
           noop
           addx 15
           addx -21
           addx 22
           addx -6
           addx 1
           noop
           addx 2
           addx 1
           noop
           addx -10
           noop
           noop
           addx 20
           addx 1
           addx 2
           addx 2
           addx -6
           addx -11
           noop
           noop
           noop
           """;

    private final InputParser<Stream<Instruction>> parser = new InstructionParser();

    @Test
    void partOneSolution_should_return_13140() {
        final Day10 day10 = new Day10(parser);
        assertThat(day10.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(13140);
    }

    @Test
    void partTwoSolution_should_return_String() {

        String expected = """
            ##..##..##..##..##..##..##..##..##..##..
            ###...###...###...###...###...###...###.
            ####....####....####....####....####....
            #####.....#####.....#####.....#####.....
            ######......######......######......####
            #######.......#######.......#######.....""";

        final Day10 day10 = new Day10(parser);
        List<Cycle> cycles = day10.getCycles(parser.parse(input.lines()));

        assertThat(day10.getScreenOutput(cycles)).isEqualTo(expected);
    }

}
