package uk.co.eandrews.advent2022.day.day3;

import lombok.Builder;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Builder
@Getter
public class BackPack {
    private final List<Character> compartment1;
    private final List<Character> compartment2;

    public List<Character> getAllContents() {
        return Stream.of(compartment1, compartment2)
            .flatMap(Collection::stream)
            .toList();
    }
}
