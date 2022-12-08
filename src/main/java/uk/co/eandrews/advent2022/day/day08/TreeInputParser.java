package uk.co.eandrews.advent2022.day.day08;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

import uk.co.eandrews.util.io.input.parser.InputParser;

@Component
public class TreeInputParser implements InputParser<Integer[][]> {

    @Override
    public Integer[][] parse(Stream<String> input) {
        return input
                .map(s -> IntStream.iterate(0, i -> i < s.length(), i -> i + 1)
                        .mapToObj(i -> s.substring(i, Math.min(s.length(), i + 1)))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new))
                .toArray(Integer[][]::new);

    }

}