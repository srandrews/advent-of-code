package uk.co.eandrews.advent2022.day.day2;

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

@Component("Day2-2022")
@ExtensionMethod({StreamEx.class, StreamExUtil.class})
public class Day2 extends Day2022<Stream<RockPaperScissorRound>, Long> {

    public Day2(final InputParser<Stream<RockPaperScissorRound>> inputParser) {
        super(2, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Stream<RockPaperScissorRound>, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Stream<RockPaperScissorRound>, Long> partOneSolution() {
        return input -> StreamEx.of(input)
            .map(RockPaperScissorRound::getPlayerScore)
            .mapToLong(value -> value)
            .sum();

    }

    public PuzzleSolution<Stream<RockPaperScissorRound>, Long> partTwoSolution() {
        return input -> StreamEx.of(input)
            .map(RockPaperScissorRound::getPlayerScoreOutcome)
            .mapToLong(value -> value)
            .sum();
    }

}
