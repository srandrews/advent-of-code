package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day07.Day7;
import uk.co.eandrews.advent2022.day.day07.Directory;
import uk.co.eandrews.advent2022.day.day07.DirectoryOperationsInputParser;
import uk.co.eandrews.util.io.input.parser.InputParser;

import static org.assertj.core.api.Assertions.assertThat;

class Day07Test {

    String input = """
       $ cd /
       $ ls
       dir a
       14848514 b.txt
       8504156 c.dat
       dir d
       $ cd a
       $ ls
       dir e
       29116 f
       2557 g
       62596 h.lst
       $ cd e
       $ ls
       584 i
       $ cd ..
       $ cd ..
       $ cd d
       $ ls
       4060174 j
       8033020 d.log
       5626152 d.ext
       7214296 k
       """;

    private final InputParser<Directory> parser = new DirectoryOperationsInputParser();

    @Test
    void partOneSolution_should_return_95437() {
        final Day7 day7 = new Day7(parser);
        assertThat(day7.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(95437);
    }

    @Test
    void partTwoSolution_should_return_24933642() {
        final Day7 day7 = new Day7(parser);
        assertThat(day7.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(24933642);
    }

}
