package uk.co.eandrews.advent2023.day.day03;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SymbolDetail {
    int position;
    String symbol;
}
