package uk.co.eandrews.util;

@FunctionalInterface
public interface PuzzleSolution<T, R> {
    R solve(T input);
}
