package uk.co.eandrews.advent2022.day.day11;

import lombok.Builder;
import lombok.Getter;

import java.util.Optional;
import java.util.Queue;
import java.util.function.DoubleFunction;

@Builder
@Getter
public class Monkey {
    private int monkeyId;
    private Queue<Double> items ;
    private DoubleFunction<Double> worryLevelFunction;
    private Double testDivisor;
    private int monkeyIfTrue;
    private int monkeyIfFalse;
    private int inspectionCount;

    public Optional<Double> getItem() {
        Double item = items.poll();
        if (item==null) {
            return Optional.empty();
        } else {
            inspectionCount++;
            return Optional.of(getWorryLevelFunction().apply(item));
        }
    }

}
