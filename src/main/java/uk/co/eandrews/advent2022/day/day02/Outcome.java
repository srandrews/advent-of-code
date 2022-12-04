package uk.co.eandrews.advent2022.day.day02;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum Outcome {
    WIN(6, "Z"),
    DRAW(3, "Y"),
    LOSE(0, "X");

    private final int score;
    private final String notation;

    public static Outcome getOutcome(String outcome) {
        return Arrays.stream(Outcome.values())
            .filter(o -> o.getNotation().equals(outcome))
            .findFirst().orElseThrow();
    }
}
