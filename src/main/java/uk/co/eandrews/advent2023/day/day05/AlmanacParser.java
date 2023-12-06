package uk.co.eandrews.advent2023.day.day05;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@Slf4j
@Component
public class AlmanacParser implements InputParser<Almanac> {

    @Override
    public Almanac parse(Stream<String> input) {

        Almanac.AlmanacBuilder almanacBuilder = Almanac.builder();
        AtomicReference<String> currentPosition = new AtomicReference<>();

        input
                .filter(StringUtils::hasText)
                .forEach(s -> {
                    if (s.startsWith("seeds:")) {
                        almanacBuilder.seeds(Arrays.stream(s.substring(7).split(" "))
                                .map(Long::parseLong)
                                .toList());
                    } else if (s.endsWith("map:")) {
                        currentPosition.set(s.substring(0, s.length()-5));
                    } else {
                        switch (currentPosition.get()) {
                            case "seed-to-soil" ->
                                almanacBuilder.seedToSoil(getAlmanacLine(s));
                            case "soil-to-fertilizer" ->
                                almanacBuilder.soilToFertilizer(getAlmanacLine(s));
                            case "fertilizer-to-water" ->
                                    almanacBuilder.fertilizerToWater(getAlmanacLine(s));
                            case "water-to-light" ->
                                    almanacBuilder.waterToLight(getAlmanacLine(s));
                            case "light-to-temperature" ->
                                    almanacBuilder.lightToTemperature(getAlmanacLine(s));
                            case "temperature-to-humidity" ->
                                    almanacBuilder.temperatureToHumidity(getAlmanacLine(s));
                            case "humidity-to-location" ->
                                    almanacBuilder.humidityToLocation(getAlmanacLine(s));

                        }
                    }
                });

        return almanacBuilder.build();
    }

    private AlmanacLine getAlmanacLine(String line) {
        String[] items = line.split(" ");
        return AlmanacLine.builder()
                .destinationRangeStart(Long.parseLong(items[0]))
                .sourceRangeStart(Long.parseLong(items[1]))
                .rangeLength(Long.parseLong(items[2]))
                .build();
    }
}
