package uk.co.eandrews.advent2022.day.day10;

import org.springframework.stereotype.Component;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

@Component
public class InstructionParser implements InputParser<Stream<Instruction>> {

    @Override
    public Stream<Instruction> parse(Stream<String> input) {
        return input
                .map(s -> s.split(" "))
                .map(strings -> switch (strings[0]) {
                    case "noop" -> new Instruction.Noop();
                    case "addx" -> new Instruction.AddX(Integer.parseInt(strings[1]));
                    default -> throw new IllegalStateException("Unexpected value: " + strings[0]);
                });
    }

}