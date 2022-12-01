package uk.co.eandrews.day.day3;

import one.util.streamex.IntStreamEx;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Component;
import uk.co.eandrews.day.Day;
import uk.co.eandrews.day.PuzzleSolution;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.List;
import java.util.stream.Stream;

@Component
public class Day3 extends Day<Stream<String>, Long> {

    public Day3(final InputParser<Stream<String>> inputParser) {
        super(3, inputParser);
    }

    @Override
    public List<PuzzleSolution<Stream<String>, Long>> getSolutions() {
        return List.of(
            partOneSolution()
        );
    }

    public PuzzleSolution<Stream<String>, Long> partOneSolution() {
        return input -> {
            final var lines = input.toList();
            final var result = IntStreamEx.range(0, lines.get(0).length()).boxed()
                .map(index -> countOnes(lines, index))
                .foldLeft(new ImmutablePair<>(new StringBuilder(), new StringBuilder()), (acc, bit) -> {
                    acc.getKey().append(bit >= 0 ? '1' : '0');
                    acc.getValue().append(bit >= 0 ? '0' : '1');
                    return acc;
                });
            return Long.parseLong(result.getKey().toString(), 2) * Long.parseLong(result.getValue().toString(), 2);
        };
    }

//    public PuzzleSolution<Stream<String>, Long> solution(final Vector3 seed) {
//        return commands -> {
//            final var result = StreamEx.of(commands).foldLeft(seed, (position, command) -> command.apply(position));
//            return result.x() * result.y();
//        };
//    }

    private long countOnes(final List<String> lines, final int index) {
        return lines.stream().mapToLong(l -> l.charAt(index) == '1' ? 1 : -1).sum();
    }


}
