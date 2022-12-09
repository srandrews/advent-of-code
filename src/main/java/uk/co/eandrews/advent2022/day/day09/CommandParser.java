package uk.co.eandrews.advent2022.day.day09;

import org.springframework.stereotype.Component;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

@Component
public class CommandParser implements InputParser<Stream<Command>> {

    @Override
    public Stream<Command> parse(Stream<String> input) {
        return input
                .map(s -> s.split(" "))
                .map(strings -> Command
                    .builder()
                    .direction(strings[0])
                    .steps(Integer.parseInt(strings[1]))
                    .build());
    }

}