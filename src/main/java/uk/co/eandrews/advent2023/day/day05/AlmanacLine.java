package uk.co.eandrews.advent2023.day.day05;

import lombok.Builder;

import java.util.Optional;

@Builder
public class AlmanacLine {
    private long destinationRangeStart;
    private long sourceRangeStart;
    private long rangeLength;

    public Optional<Long> getDestination(Long source) {
        if (sourceRangeStart <= source && sourceRangeStart+rangeLength > source) {
            return Optional.of(source - sourceRangeStart + destinationRangeStart);
        } else {
            return Optional.empty();
        }
    }
}
