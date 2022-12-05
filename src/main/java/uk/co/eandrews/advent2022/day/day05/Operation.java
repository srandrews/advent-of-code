package uk.co.eandrews.advent2022.day.day05;

import lombok.Builder;
import lombok.Getter;

import java.util.*;
import java.util.stream.IntStream;

@Builder
@Getter
public class Operation {
    private final int quantity;
    private final int from;
    private final int to;

    public void apply(Map<Integer, Stack<String>> stacks) {
        IntStream.range(1, quantity+1)
            .forEach(value -> {
                String item = stacks.get(from).pop();
                stacks.get(to).push(item);
            });
    }

    public void applyBulk(Map<Integer, Stack<String>> stacks) {
        List<String> move = new ArrayList<>(IntStream.range(1, quantity+1)
            .mapToObj(value -> stacks.get(from).pop())
            .toList());

        Collections.reverse(move);

        move.forEach(s -> stacks.get(to).push(s));
    }
}
