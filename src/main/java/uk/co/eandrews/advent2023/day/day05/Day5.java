package uk.co.eandrews.advent2023.day.day05;

import com.google.common.base.CharMatcher;
import one.util.streamex.LongStreamEx;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2023.Day2023;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Component("Day5-2023")
public class Day5 extends Day2023<Almanac, Long> {

    public Day5(final InputParser<Almanac> inputParser) {
        super(5, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Almanac, Long>> getSolutions() {
        return List.of(
            partOneSolution()
        //    partTwoSolution()
        );
    }

    public PuzzleSolution<Almanac, Long> partOneSolution() {
        return input -> input.getSeeds()
                    .parallelStream()
                    .map(seed -> getOutput(input.getSeedToSoilMap(), seed))
                    .map(soil  -> getOutput(input.getSoilToFertilizerMap(), soil))
                    .map(fertilizer -> getOutput(input.getFertilizerToWaterMap(), fertilizer))
                    .map(water -> getOutput(input.getWaterToLightMap(), water))
                    .map(light -> getOutput(input.getLightToTemperatureMap(), light))
                    .map(temperature -> getOutput(input.getTemperatureToHumidityMap(), temperature))
                    .map(humidity -> getOutput(input.getHumidityToLocationMap(), humidity))
                             .mapToLong(Long::longValue)
                         .min()
                      .orElse(0);
    }

    public PuzzleSolution<Almanac, Long> partTwoSolution() {

        return input -> {

            Collection<Pair<Long, Long>> seedRange = new ArrayList<>();

            ArrayList<Long> seedCollection = new ArrayList<>(input.getSeeds());
            for (int i = 0; i<seedCollection.size(); i+=2) {
                seedRange.add(Pair.of(seedCollection.get(i), seedCollection.get(i+1)));
            }

            return seedRange
                    .parallelStream()
                    .map(pair -> LongStreamEx.range(pair.getLeft(), pair.getLeft()+pair.getRight())
                                .parallel()
                                .map(seed -> getOutput(input.getSeedToSoilMap(), seed))
                                .map(soil  -> getOutput(input.getSoilToFertilizerMap(), soil))
                                .map(fertilizer -> getOutput(input.getFertilizerToWaterMap(), fertilizer))
                                .map(water -> getOutput(input.getWaterToLightMap(), water))
                                .map(light -> getOutput(input.getLightToTemperatureMap(), light))
                                .map(temperature -> getOutput(input.getTemperatureToHumidityMap(), temperature))
                                .map(humidity -> getOutput(input.getHumidityToLocationMap(), humidity))
                                .min()
                                .orElse(0)
                    )
                    .mapToLong(Long::longValue)
                    .min()
                    .orElse(0);
        };
    }

    private Long getOutput(Collection<AlmanacLine> lines, Long source) {
        return lines.stream()
                .map(line -> line.getDestination(source))
                .filter(Optional::isPresent)
                .findAny()
                .orElse(Optional.of(source))
                .get();
    }

}
