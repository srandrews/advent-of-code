package uk.co.eandrews.advent2023.day.day02;

import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2023.Day2023;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Component("Day2-2023")
public class Day2 extends Day2023<Stream<Game>, Long> {

    private static final int MAX_BLUE = 14;
    private static final int MAX_GREEN = 13;
    private static final int MAX_RED = 12;



    public Day2(final InputParser<Stream<Game>> inputParser) {
        super(2, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Stream<Game>, Long>> getSolutions() {
        return List.of(
                partOneSolution(),
                partTwoSolution()
        );
    }

    public PuzzleSolution<Stream<Game>, Long> partOneSolution() {
        return input -> input.filter(this::possible)
                .mapToLong(Game::getId)
                .sum();
    }

    public PuzzleSolution<Stream<Game>, Long> partTwoSolution() {
        return input -> input.map(this::getLowestPossibleRound)
                .mapToLong(round -> round.getRed() * round.getGreen() * round.getBlue())
                .sum();
    }

    private Round getLowestPossibleRound(Game game) {
        return game.getRounds().stream()
                .reduce(this::getHighestPossibleRound)
                .orElse(Round.builder().build());
    }

    private Round getHighestPossibleRound(Round round1, Round round2) {
        return Round.builder()
                .blue(Math.max(round1.getBlue(), round2.getBlue()))
                .green(Math.max(round1.getGreen(), round2.getGreen()))
                .red(Math.max(round1.getRed(), round2.getRed()))
                .build();
    }

    private boolean possible(Game game) {
        return game.getRounds()
                .stream()
                .noneMatch(this::impossibleRound);
    }

    private boolean impossibleRound(Round round) {
        return round.getBlue()>MAX_BLUE
                || round.getGreen()>MAX_GREEN
                || round.getRed()>MAX_RED;
    }

}
