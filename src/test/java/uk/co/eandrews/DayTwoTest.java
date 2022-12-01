package uk.co.eandrews;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.day.day1.Day1;
import uk.co.eandrews.day.day2.Command;
import uk.co.eandrews.day.day2.CommandInputParser;
import uk.co.eandrews.day.day2.Day2;
import uk.co.eandrews.util.Vector2;
import uk.co.eandrews.util.Vector3;
import uk.co.eandrews.util.io.input.parser.InputParser;
import uk.co.eandrews.util.io.input.resolver.InputResolver;

import java.util.List;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

class DayTwoTest {

    private List<String> exampleInput  = List.of("forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2");

    private final List<Command> exampleParsedInput = List.of(
        new Command.Forward(5),
        new Command.Down(5),
        new Command.Forward(8),
        new Command.Up(3),
        new Command.Down(8),
        new Command.Forward(2)
    );

    private final InputResolver dummyInputResolver = day -> exampleInput.stream();

    @Test
    void partOneSolution_should_return_150() {
        final Day2 day2 = new Day2(new CommandInputParser());
        assertThat(day2.solution(Vector2.ZERO).solve(exampleParsedInput.stream())).isEqualTo(150);
    }

    @Test
    void partTwoSolution_should_return_900() {
        final Day2 day2 = new Day2(new CommandInputParser());
        assertThat(day2.solution(Vector3.ZERO).solve(exampleParsedInput.stream())).isEqualTo(900);
    }

    @Test
    void solve_should_equal_7And5() {
        final Day2 day2 = new Day2(new CommandInputParser());
        assertThat(day2.solve(dummyInputResolver)).isEqualTo(List.of(150L, 900L));
    }

}