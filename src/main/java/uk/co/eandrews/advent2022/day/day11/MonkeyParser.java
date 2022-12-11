package uk.co.eandrews.advent2022.day.day11;

import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Component;
import uk.co.eandrews.util.StreamExUtil;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.*;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@ExtensionMethod({StreamExUtil.class})
public class MonkeyParser implements InputParser<Map<Integer, Monkey>> {

    private static final String OPERATION_BREAKDOWN_REGEX = "new = old (.) ((\\d+)|old)";
    private static final Pattern OPERATION_BREAKDOWN_PATTERN = Pattern.compile(OPERATION_BREAKDOWN_REGEX);

    private static final String TEST_OPERATION_BREAKDOWN_REGEX = "(.+) by (\\d+)";
    private static final Pattern testOperationBreakdownPattern = Pattern.compile(TEST_OPERATION_BREAKDOWN_REGEX);

    private static final String MONKEY_ID_REGEX = "Monkey (\\d+):";
    private static final String STARTING_ITEMS_REGEX = ".*Starting items: (.+)";
    private static final String OPERATION_REGEX = ".*Operation: (.+)";
    private static final String TEST_OPERATION_REGEX = ".*Test: (.+)";
    private static final String IF_TRUE_REGEX = ".*If true: throw to monkey (\\d+)";
    private static final String IF_FALSE_REGEX = ".*If false: throw to monkey (\\d+)";

    private final Map<MonkeyDetailName, Pattern> regexPatterns;

    public MonkeyParser() {
        regexPatterns = Map.of(
                MonkeyDetailName.ID, Pattern.compile(MONKEY_ID_REGEX),
                MonkeyDetailName.STARTING_ITEMS, Pattern.compile(STARTING_ITEMS_REGEX),
                MonkeyDetailName.OPERATION, Pattern.compile(OPERATION_REGEX),
                MonkeyDetailName.TEST, Pattern.compile(TEST_OPERATION_REGEX),
                MonkeyDetailName.IF_TRUE, Pattern.compile(IF_TRUE_REGEX),
                MonkeyDetailName.IF_FALSE, Pattern.compile(IF_FALSE_REGEX)
            );
    }

    @Override
    public Map<Integer, Monkey> parse(Stream<String> input) {
        return input.toList().ofVariableSubLists(String::isEmpty)
            .map(monkeyDetails -> {
                Monkey.MonkeyBuilder monkeyBuilder = Monkey.builder();
                monkeyDetails.forEach(monkeyDetail -> setMonkeyValue(monkeyBuilder, monkeyDetail));
                return monkeyBuilder.build();
            }).collect(Collectors.toMap(
                Monkey::getMonkeyId,
                Function.identity(),
                (o1, o2) -> o1,
                TreeMap::new));
    }

    private void setMonkeyValue(Monkey.MonkeyBuilder builder, String detail) {
        regexPatterns.forEach((key, value) -> {
            Matcher m = value.matcher(detail);
            if (m.matches()) {
                switch (key) {
                    case ID -> builder.monkeyId(Integer.parseInt(m.group(1)));
                    case STARTING_ITEMS -> builder.items(getQueue(m.group(1)));
                    case OPERATION -> builder.worryLevelFunction(getWorryFunction(m.group(1)));
                    case TEST ->  builder.testDivisor(getTestDivisor(m.group(1)));
                    case IF_FALSE -> builder.monkeyIfFalse(Integer.parseInt(m.group(1)));
                    case IF_TRUE -> builder.monkeyIfTrue(Integer.parseInt(m.group(1)));
                    default -> throw new IllegalStateException("Unexpected value: " + key);
                }
            }
        });
    }

    private Queue<Double> getQueue(String input) {
        return Arrays.stream(input.split(", "))
            .map(Double::parseDouble)
            .collect(Collectors.toCollection(PriorityQueue::new));
    }

    private DoubleFunction<Double> getWorryFunction(String input) {
        Matcher m = OPERATION_BREAKDOWN_PATTERN.matcher(input);
        if (m.matches()) {
            return value -> {
                double secondValue = ("old".equals(m.group(2))?value:Long.parseLong(m.group(2)));
                return switch (m.group(1)) {
                    case "*" -> value * secondValue;
                    case "+" -> value + secondValue;
                    case "-" -> value - secondValue;
                    case "/" -> value / secondValue;
                    default -> throw new IllegalStateException("Unexpected value: " + m.group(1));
                };
            };
        } else {
            throw new IllegalArgumentException("Worry function input invalid: "+input);
        }
    }

    private Double getTestDivisor(String input) {
        Matcher m = testOperationBreakdownPattern.matcher(input);
        if (m.matches()) {
            if ("divisible".equals(m.group(1))) {
                return Double.parseDouble(m.group(2));
            } else {
                throw new IllegalArgumentException("Not sure what to do with: "+m.group(1));
            }
        } else {
            throw new IllegalArgumentException("Test Function input invalid: "+input);
        }
    }

    private enum MonkeyDetailName {
        ID,
        STARTING_ITEMS,
        OPERATION,
        TEST,
        IF_FALSE,
        IF_TRUE
    }

}