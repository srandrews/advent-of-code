package uk.co.eandrews.advent2022.day.day05;

import org.springframework.stereotype.Component;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
public class CraneOperationInputParser implements InputParser<CraneOperation> {
    @Override
    public CraneOperation parse(Stream<String> input) {

        List<List<String>> groups = input
            .reduce(new ArrayList<>(),
                (list, s) -> {
                    if (list.isEmpty()) {
                        list.add(new ArrayList<>());
                    }
                    if (s.isEmpty()) {
                        list.add(new ArrayList<>());
                    } else {
                        list.get(list.size() - 1).add(s);
                    }
                    return list;
                },
                (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                });

        return CraneOperation.builder()
            .stacks(getStacks(groups.get(0)))
            .operations(getOperations(groups.get(1)))
            .build();

    }

    private Map<Integer, Stack<String>> getStacks(List<String> input) {

        Collections.reverse(input);

        String stackIds = input.get(0);
        Map<Integer, Stack<String>> stacks = new HashMap<>();

        Arrays.stream(stackIds.split(" ")).toList().stream().filter(s -> !s.isEmpty())
            .map(Integer::parseInt)
            .forEach(integer -> stacks.put(integer, new Stack<>()));

        final String regex = "(\\[\\w]|.\\s{3})";
        final Pattern pattern = Pattern.compile(regex);

        input.stream()
            .skip(1)
            .map(pattern::matcher)
            .forEach(matcher -> {
                int count = 1;
                while (matcher.find()) {
                    String matched  = matcher.group(1);
                    if (!matched.isBlank()) {
                        stacks.get(count).add(String.valueOf(matched.charAt(1)));
                    }
                    count++;
                }
            });

        return stacks;
    }

    private List<Operation> getOperations(List<String> input) {

        final String regex = "move (\\d+) from (\\d+) to (\\d+)*";
        final Pattern pattern = Pattern.compile(regex);

        return input.stream()
            .map(pattern::matcher)
            .filter(Matcher::matches)
            .map(matcher -> Operation.builder()
                .quantity(Integer.parseInt(matcher.group(1)))
                .from(Integer.parseInt(matcher.group(2)))
                .to(Integer.parseInt(matcher.group(3)))
                .build())
            .toList();
    }
}