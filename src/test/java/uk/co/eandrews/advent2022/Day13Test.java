package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day13.Day13;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day13Test {

    String input = """
        [1,1,3,1,1]
        [1,1,5,1,1]
        
        [[1],[2,3,4]]
        [[1],4]
        
        [9]
        [[8,7,6]]
        
        [[4,4],4,4]
        [[4,4],4,4,4]
        
        [7,7,7,7]
        [7,7,7]
        
        []
        [3]
        
        [[[]]]
        [[]]
        
        [1,[2,[3,[4,[5,6,7]]]],8,9]
        [1,[2,[3,[4,[5,6,0]]]],8,9]
        """;

    private final InputParser<Stream<String>> parser = inputResolver -> input.lines();

    @Test
    void partOneSolution_should_return_13() {
        final Day13 day13 = new Day13(parser);
        assertThat(day13.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(13);
    }

    @Test
    void partTwoSolution_should_return_140() {
        final Day13 day13 = new Day13(parser);
        assertThat(day13.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(140L);
    }

}
