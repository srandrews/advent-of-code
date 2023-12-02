package uk.co.eandrews.advent2023.day.day02;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Collection;

@Value
@Builder(toBuilder = true)
public class Game {
    int id;
    @Singular
    Collection<Round> rounds;
}
