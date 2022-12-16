package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day16.Day16;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day16Test {

    String input = """
        Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
        Valve BB has flow rate=13; tunnels lead to valves CC, AA
        Valve CC has flow rate=2; tunnels lead to valves DD, BB
        Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
        Valve EE has flow rate=3; tunnels lead to valves FF, DD
        Valve FF has flow rate=0; tunnels lead to valves EE, GG
        Valve GG has flow rate=0; tunnels lead to valves FF, HH
        Valve HH has flow rate=22; tunnel leads to valve GG
        Valve II has flow rate=0; tunnels lead to valves AA, JJ
        Valve JJ has flow rate=21; tunnel leads to valve II
        """;

    private final InputParser<Stream<String>> parser = inputResolver -> input.lines();

    @Test
    void partOneSolution_should_return_1651() {
        final Day16 day16 = new Day16(parser);
        assertThat(day16.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(1651);
    }

    @Test
    void partTwoSolution_should_return_93() {
        final Day16 day16 = new Day16(parser);
        assertThat(day16.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(93L);
    }

}
