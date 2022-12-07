package uk.co.eandrews.advent2022.day.day07;

import lombok.experimental.ExtensionMethod;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.StreamExUtil;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Component("Day7-2022")
@ExtensionMethod({StreamEx.class, StreamExUtil.class})
public class Day7 extends Day2022<Directory, Long> {

    public Day7(final InputParser<Directory> inputParser) {
        super(7, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Directory, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Directory, Long> partOneSolution() {
        return input -> flatten(input)
            .filter(directory -> directory.size()<100000)
            .mapToLong(Directory::size)
            .sum();
    }

    public PuzzleSolution<Directory, Long> partTwoSolution() {
        return input -> flatten(input)
            .filter(directory -> directory.size() > input.size()-40000000)
            .mapToLong(Directory::size)
            .min().orElse(0);
    }

    private Stream<Directory> flatten(Directory d) {
        return Stream.concat(
            Stream.of(d),
            d.getDirectories().values().stream().flatMap(this::flatten));
    }

}
