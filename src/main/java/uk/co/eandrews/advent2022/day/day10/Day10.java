package uk.co.eandrews.advent2022.day.day10;

import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.StreamExUtil;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component("Day10-2022")
@ExtensionMethod({StreamEx.class, StreamExUtil.class})
@Slf4j
public class Day10 extends Day2022<Stream<Instruction>, Long> {

    public Day10(final InputParser<Stream<Instruction>> inputParser) {
        super(10, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Stream<Instruction>, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Stream<Instruction>, Long> partOneSolution() {
        return input -> {
            List<Cycle> cycles = getCycles(input);
            return IntStream.of(20, 60, 100, 140, 180, 220)
                .map(cycle -> cycles.get(cycle-1).getDuringCycle()*cycle)
                .mapToLong(Long::valueOf)
                .sum();
        };
    }

    public PuzzleSolution<Stream<Instruction>, Long> partTwoSolution() {
        return input -> {
            List<Cycle> cycles = getCycles(input);
            log.info("\n\n"+getScreenOutput(cycles)+"\n");
            return 0L;
        };
    }

    public List<Cycle> getCycles(Stream<Instruction> instructions) {
        List<Cycle> cycles = new ArrayList<>();
        instructions.forEach(instruction -> instruction.apply(cycles));
        return cycles;
    }

    public String getScreenOutput(List<Cycle> cycles) {
        int x = 40;
        int y = 6;

        return IntStream.range(0,y)
                .mapToObj(row -> IntStream.range(0, x)
                    .mapToObj(column -> {
                        Cycle cycle = cycles.get((row*x)+column);
                        return pixelCoveredBySprite(column, cycle)?"#":".";
                    }).collect(Collectors.joining())).collect(Collectors.joining("\n"));

    }

    private boolean pixelCoveredBySprite(int column, Cycle cycle) {
        return (cycle.getDuringCycle()>=column-1) && (cycle.getDuringCycle()<=column+1);
    }
}