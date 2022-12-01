package uk.co.eandrews.day;

@FunctionalInterface
public interface PuzzleSolution<T, R> {
    R solve(T input);
}
