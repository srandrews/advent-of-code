package uk.co.eandrews.advent2023.day.day06;

import com.google.common.base.CharMatcher;
import org.apache.commons.lang3.tuple.Pair;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import uk.co.eandrews.advent2023.Day2023;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Component("Day6-2023")
public class Day6 extends Day2023<Stream<String>, Long> {

    public Day6(final InputParser<Stream<String>> inputParser) {
        super(6, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Stream<String>, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
                partTwoSolution()
        );
    }

    public PuzzleSolution<Stream<String>, Long> partOneSolution() {
        return input -> getPairs(input)
                    .stream()
                    .map(this::getWinningPossibilities)
                    .mapToLong(Long::longValue)
                    .reduce((left, right) -> left*right)
                    .orElse(0);
    }

    public PuzzleSolution<Stream<String>, Long> partTwoSolution() {
        return input ->  {
            Pair<Long, Long> pair = getPair(input);
            return getWinningPossibilities(pair);
        };
    }

    private long getWinningPossibilities(Pair<Long, Long> pair) {
        return LongStream.rangeClosed(0,pair.getLeft())
                .map(time -> (pair.getLeft()-time)*time)
                .filter(value -> value>pair.getRight())
                .count();
    }

    private Pair<Long, Long> getPair(Stream<String> input) {
        AtomicLong time = new AtomicLong();
        AtomicLong distance = new AtomicLong();
        input.forEach(s -> {
            if (s.startsWith("Time:")) {
                String data = s.substring(5).trim();
                String[] timeStrs = data.split(" ");
                time.set(Long.parseLong(Arrays.stream(timeStrs)
                        .filter(StringUtils::hasText)
                        .collect(Collectors.joining(""))));

            } else {
                String data = s.substring(9).trim();
                String[] distaneStr = data.split(" ");
                distance.set(Long.parseLong(Arrays.stream(distaneStr)
                        .filter(StringUtils::hasText)
                        .collect(Collectors.joining(""))));
            }
        });

        return Pair.of(time.get(), distance.get());
    }

    private Collection<Pair<Long, Long>> getPairs(Stream<String> input) {
        List<Long> times = new ArrayList<>();
        List<Long> distance = new ArrayList<>();

        input.forEach(s -> {
            if (s.startsWith("Time:")) {
                String data = s.substring(5).trim();
                String[] timeStrs = data.split(" ");
                Arrays.stream(timeStrs)
                        .filter(StringUtils::hasText)
                        .map(Long::parseLong)
                        .collect(Collectors.toCollection(() -> times));
            } else {
                String data = s.substring(9).trim();
                String[] distaneStr = data.split(" ");
                Arrays.stream(distaneStr)
                        .filter(StringUtils::hasText)
                        .map(Long::parseLong)
                        .collect(Collectors.toCollection(() -> distance));
            }
        });

        Collection<Pair<Long, Long>> pairs = new ArrayList<>();

        for (int i=0; i<times.size(); i++) {
            pairs.add(Pair.of(times.get(i), distance.get(i)));
        }

        return pairs;
    }


}
