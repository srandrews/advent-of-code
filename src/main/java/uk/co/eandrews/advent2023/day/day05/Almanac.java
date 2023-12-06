package uk.co.eandrews.advent2023.day.day05;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Collection;

@Builder
@Getter
public class Almanac {
    private Collection<Long> seeds;
    @Singular("seedToSoil")
    private Collection<AlmanacLine> seedToSoilMap;
    @Singular("soilToFertilizer")
    private Collection<AlmanacLine> soilToFertilizerMap;
    @Singular("fertilizerToWater")
    private Collection<AlmanacLine> fertilizerToWaterMap;
    @Singular("waterToLight")
    private Collection<AlmanacLine> waterToLightMap;
    @Singular("lightToTemperature")
    private Collection<AlmanacLine> lightToTemperatureMap;
    @Singular("temperatureToHumidity")
    private Collection<AlmanacLine> temperatureToHumidityMap;
    @Singular("humidityToLocation")
    private Collection<AlmanacLine> humidityToLocationMap;
}
