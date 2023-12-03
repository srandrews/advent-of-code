package uk.co.eandrews.advent2023.day.day03;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Schematic {
    @Singular
    List<PartDetail> partDetails;
    @Singular
    List<SymbolDetail> symbolDetails;
}
