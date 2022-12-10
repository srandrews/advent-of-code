package uk.co.eandrews.advent2022.day.day10;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class Cycle {
    private final int duringCycle;
    private final int afterCycle;
}
