package uk.co.eandrews.advent2022.day.day04;

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

@Component("Day4-2022")
@ExtensionMethod({StreamEx.class, StreamExUtil.class})
public class Day4 extends Day2022<Stream<SectionAssignment>, Long> {

    public Day4(final InputParser<Stream<SectionAssignment>> inputParser) {
        super(4, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Stream<SectionAssignment>, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Stream<SectionAssignment>, Long> partOneSolution() {
        return input -> input.filter(SectionAssignment::fullIntersect).count();
    }

    public PuzzleSolution<Stream<SectionAssignment>, Long> partTwoSolution() {
       return input -> input.filter(SectionAssignment::partialIntersect).count();
    }

}
