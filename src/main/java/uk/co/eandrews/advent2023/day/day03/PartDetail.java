package uk.co.eandrews.advent2023.day.day03;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PartDetail {
    int partNumber;
    int startPosition;
    int endPosition;
}
