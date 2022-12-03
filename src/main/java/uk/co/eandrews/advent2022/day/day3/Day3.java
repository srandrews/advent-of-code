package uk.co.eandrews.advent2022.day.day3;

import lombok.experimental.ExtensionMethod;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.StreamExUtil;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component("Day3-2022")
@ExtensionMethod({StreamEx.class, StreamExUtil.class})
public class Day3 extends Day2022<Stream<BackPack>, Long> {

    public Day3(final InputParser<Stream<BackPack>> inputParser) {
        super(3, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Stream<BackPack>, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Stream<BackPack>, Long> partOneSolution() {
        return input -> StreamEx.of(input)
            .map(backPack -> backPack.getCompartment1().stream()
                .filter(c -> backPack.getCompartment2().contains(c))
                .distinct()
                .map(this::getCharacterValue)
                .mapToInt(value -> value))
            .mapToLong(IntStream::sum)
            .sum();
    }

    public PuzzleSolution<Stream<BackPack>, Long> partTwoSolution() {
        return input -> 0L;
    }

    private int getCharacterValue(Character c) {
        if (Character.isUpperCase(c)) {
            return c - 38;
        } else {
            return c - 96;
        }
    }

}
