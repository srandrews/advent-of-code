package uk.co.eandrews.advent2022.day.day05;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Stack;

@Getter
@Builder
public class CraneOperation {
    private final Map<Integer, Stack<String>> stacks;
    private final List<Operation> operations;
}
