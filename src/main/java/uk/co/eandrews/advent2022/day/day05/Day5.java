package uk.co.eandrews.advent2022.day.day05;

import lombok.experimental.ExtensionMethod;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.StreamExUtil;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component("Day5-2022")
@ExtensionMethod({StreamEx.class, StreamExUtil.class})
public class Day5 extends Day2022<CraneOperation, String> {

    public Day5(final InputParser<CraneOperation> inputParser) {
        super(5, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<CraneOperation, String>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<CraneOperation, String> partOneSolution() {
        return input -> {
                input.getOperations()
                    .forEach(operation -> operation.apply(input.getStacks()));
            return peekTop(input);
        };
    }

    public PuzzleSolution<CraneOperation, String> partTwoSolution() {
       return input -> {
            input.getOperations()
                .forEach(operation -> operation.applyBulk(input.getStacks()));
            return peekTop(input);
        };
    }

    private String peekTop(CraneOperation craneOperation) {
        return IntStream.range(1, craneOperation.getStacks().size()+1)
            .mapToObj(stackId -> craneOperation.getStacks().get(stackId))
            .map(Stack::peek)
            .collect(Collectors.joining());
    }


}
