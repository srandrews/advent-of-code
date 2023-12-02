package uk.co.eandrews.advent2023.day.day02;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Round {
    int blue;
    int green;
    int red;
}
