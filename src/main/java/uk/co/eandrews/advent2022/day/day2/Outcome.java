package uk.co.eandrews.advent2022.day.day2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Outcome {
    WIN(6),
    DRAW(3),
    LOSE(0);

    private final int score;
}
