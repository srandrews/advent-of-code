package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day11.Day11;
import uk.co.eandrews.advent2022.day.day11.Monkey;
import uk.co.eandrews.advent2022.day.day11.MonkeyParser;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Day11Test {

    String input = """
        Monkey 0:
          Starting items: 79, 98
          Operation: new = old * 19
          Test: divisible by 23
            If true: throw to monkey 2
            If false: throw to monkey 3
        
        Monkey 1:
          Starting items: 54, 65, 75, 74
          Operation: new = old + 6
          Test: divisible by 19
            If true: throw to monkey 2
            If false: throw to monkey 0
        
        Monkey 2:
          Starting items: 79, 60, 97
          Operation: new = old * old
          Test: divisible by 13
            If true: throw to monkey 1
            If false: throw to monkey 3
        
        Monkey 3:
          Starting items: 74
          Operation: new = old + 3
          Test: divisible by 17
            If true: throw to monkey 0
            If false: throw to monkey 1
           """;

    private final InputParser<Map<Integer, Monkey>> parser = new MonkeyParser();

    @Test
    void partOneSolution_should_return_10605() {
        final Day11 day11 = new Day11(parser);
        assertThat(day11.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(10605);
    }

    @Test
    void partTwoSolution_should_return_2713310158() {
        final Day11 day11 = new Day11(parser);
        assertThat(day11.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(2713310158L);
    }

}
