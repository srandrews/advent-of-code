package uk.co.eandrews.advent2022.day.day3;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BackPack {
    private final List<Character> compartment1;
    private final List<Character> compartment2;
}
