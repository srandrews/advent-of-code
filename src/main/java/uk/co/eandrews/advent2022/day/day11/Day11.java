package uk.co.eandrews.advent2022.day.day11;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.*;

@Component("Day11-2022")
@Slf4j
public class Day11 extends Day2022<Map<Integer, Monkey>, Long> {

    public Day11(final InputParser<Map<Integer, Monkey>> inputParser) {
        super(11, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Map<Integer, Monkey>, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Map<Integer, Monkey>, Long> partOneSolution() {
        return input -> {
            doRounds(input, 20, 3);
            return getMonkeyBusiness(input);
        };
    }

    public PuzzleSolution<Map<Integer, Monkey>, Long> partTwoSolution() {
        return input -> {
            doRounds(input, 10000, 1);
            return getMonkeyBusiness(input);
        };
    }

    private Long getMonkeyBusiness(Map<Integer, Monkey> monkeys) {
        return monkeys
            .values()
            .stream()
            .mapToLong(Monkey::getInspectionCount)
            .boxed()
            .sorted(Collections.reverseOrder())
            .limit(2)
            .reduce(1L, (a, b) -> a * b);
    }

    private void doRounds(Map<Integer, Monkey> monkeys, int rounds, int reliefDivisor) {

        double bigModulo = monkeys.values().stream()
            .map(Monkey::getTestDivisor)
            .reduce(1.0, (a, b) -> a * b);


        for (int i=0; i<rounds;i++) {
            monkeys.forEach((key, monkey) -> {
                Optional<Double> item = monkey.getItem();
                while (item.isPresent()) {
                    double itemValue = item.get();
                    itemValue = Math.floor(itemValue / reliefDivisor);

                    Double testDivisor = monkey.getTestDivisor();

                    Monkey toMonkey;

                    if (itemValue%testDivisor==0) {
                        toMonkey = monkeys.get(monkey.getMonkeyIfTrue());
                    } else {
                        toMonkey = monkeys.get(monkey.getMonkeyIfFalse());
                    }

                    toMonkey.getItems().add(itemValue%bigModulo);

                    item = monkey.getItem();
                }
            });
        }
    }

}