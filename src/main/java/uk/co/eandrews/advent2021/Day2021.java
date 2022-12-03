package uk.co.eandrews.advent2021;

import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;

import org.springframework.util.StopWatch;

import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.io.input.parser.InputParser;
import uk.co.eandrews.util.io.input.resolver.InputResolver;

import java.util.Collection;
import java.util.stream.IntStream;

@Slf4j
public abstract class Day2021<T, V> {

    private final int day;
    private final InputParser<T> inputParser;

    protected Day2021(final int day, final InputParser<T> inputParser) {
        this.day = day;
        this.inputParser = inputParser;
    }

    public int getDay() {
        return day;
    }

    public InputParser<T> getInputParser() {
        return inputParser;
    }

    public Collection<V> solve(final InputResolver resolver) {
        final Collection<PuzzleSolution<T, V>> solutions = getSolutions();
        final StopWatch stopWatch = new StopWatch("Day %d".formatted(day));
        final Collection<V> results = StreamEx.of(solutions).zipWith(IntStream.rangeClosed(1, solutions.size())).map(solution -> {
            stopWatch.start("Part %d Solution".formatted(solution.getValue()));
            final V result = solution.getKey().solve(inputParser.parse(resolver.resolve(day)));
            stopWatch.stop();
            return result;
        }).toList();
        log.info(stopWatch.prettyPrint());
        return results;
    }

    public abstract Collection<PuzzleSolution<T, V>> getSolutions();
}

