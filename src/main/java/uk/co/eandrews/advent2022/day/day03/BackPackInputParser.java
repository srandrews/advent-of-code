package uk.co.eandrews.advent2022.day.day03;

import org.springframework.stereotype.Component;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

@Component
public class BackPackInputParser implements InputParser<Stream<BackPack>> {
    @Override
    public Stream<BackPack> parse(Stream<String> input) {
        return input.map(line -> {
            final int mid = line.length() / 2; //get the middle of the String
            String[] compartmentContents = {line.substring(0, mid),line.substring(mid)};

            return BackPack.builder()
                .compartment1(compartmentContents[0].chars()
                    .mapToObj(c -> (char)c)
                    .toList())
                .compartment2(compartmentContents[1].chars()
                    .mapToObj(c -> (char)c)
                    .toList())
                .build();
        });
    }
}