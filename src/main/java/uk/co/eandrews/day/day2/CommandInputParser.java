package uk.co.eandrews.day.day2;

import org.springframework.stereotype.Component;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

@Component
public class CommandInputParser implements InputParser<Stream<Command>> {
    @Override
    public Stream<Command> parse(Stream<String> input) {
        return input.map(line -> {
           final String[] command = line.split(" ");
           return switch (command[0]) {
               case "forward" -> new Command.Forward(Long.parseLong(command[1]));
               case "down" -> new Command.Down(Long.parseLong(command[1]));
               case "up" -> new Command.Up(Long.parseLong(command[1]));
               default -> throw new IllegalStateException("Unexpected value: %s".formatted(command[0]));
           };
        });
    }
}
