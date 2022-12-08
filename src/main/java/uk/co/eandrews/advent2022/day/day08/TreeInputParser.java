package uk.co.eandrews.advent2022.day.day08;

import org.springframework.stereotype.Component;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

@Component
public class TreeInputParser implements InputParser<Integer[][]> {

    @Override
    public Integer[][] parse(Stream<String> input) {
        return input
                .map(s -> s.chars()
                        .mapToObj(i -> (char)i)
                        .map(String::valueOf)
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new))
                .toArray(Integer[][]::new);

    }

}