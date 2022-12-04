package uk.co.eandrews.advent2022.day.day04;

import org.springframework.stereotype.Component;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class SectionAssignmentInputParser implements InputParser<Stream<SectionAssignment>> {
    @Override
    public Stream<SectionAssignment> parse(Stream<String> input) {
        return input.map(line -> {
            String[] assignments = line.split(",");
            String[] elfOne = assignments[0].split("-");
            String[] elfTwo = assignments[1].split("-");

            return SectionAssignment.builder()
                .elfOneAssignments(IntStream.range(Integer.parseInt(elfOne[0]), Integer.parseInt(elfOne[1])+1)
                    .boxed()
                    .collect(Collectors.toSet()))
                .elfTwoAssignments(IntStream.range(Integer.parseInt(elfTwo[0]), Integer.parseInt(elfTwo[1])+1)
                    .boxed()
                    .collect(Collectors.toSet()))
                .build();
        });
    }
}