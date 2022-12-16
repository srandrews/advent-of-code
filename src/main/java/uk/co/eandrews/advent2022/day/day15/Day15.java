package uk.co.eandrews.advent2022.day.day15;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.Vector2;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component("Day15-2022")
public class Day15 extends Day2022<Stream<String>, Long> {

    private long row;
    private int min;
    private int max;

    @Autowired
    public void setRow(@Value("${day15.row}") int row) {
        this.row = row;
    }

    @Autowired
    public void setMin(@Value("${day15.min}") int min) {
        this.min = min;
    }

    @Autowired
    public void setMax(@Value("${day15.max}") int max) {
        this.max = max;
    }


    public Day15(final InputParser<Stream<String>> inputParser) {
        super(15, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Stream<String>, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Stream<String>, Long> partOneSolution() {
        return input -> {
            List<SensorBeaconPair> inputMap = getInputMap(input);
            Map<Vector2, Character> output = new HashMap<>();
            addSensorsAndBeacons(inputMap, output, row);

            inputMap.stream()
                .filter(sensorBeaconPair -> sensorBeaconPair.radiusIntersectsRow(row))
                .forEach(sensorBeaconPair -> {
                    Map<Vector2, Character> tmpOutput = sensorBeaconPair.pointsOnRow(row);
                    tmpOutput.forEach(output::putIfAbsent);
                });

            return output.values()
                .stream()
                .filter(character -> character.equals('#'))
                .count();
        };
    }

    public PuzzleSolution<Stream<String>, Long> partTwoSolution() {
        return input -> {
            List<SensorBeaconPair> sensorBeaconPairs = getInputMap(input);
            Vector2 missing = null;

            for (int i=min; i<=max; i++) {
                if (missing!=null) {
                    break;
                }

                List<List<Vector2>> vectors = new ArrayList<>();

                for (SensorBeaconPair sensorBeaconPair : sensorBeaconPairs) {
                    if (sensorBeaconPair.radiusIntersectsRow(i)) {
                        List<Vector2> tmpOutput = sensorBeaconPair.startAndEndpointsOnRow(i, max, min);
                        vectors.add(tmpOutput);
                    }
                }

                final List<Range> ranges = new ArrayList<>();

                vectors.forEach(vector2s -> {
                    if (vector2s.size()==2) {
                        Vector2 first = vector2s.get(0);
                        Vector2 second = vector2s.get(1);
                        if (first.x()<second.x()) {
                            ranges.add(new Range(first.x(), second.x()));
                        } else {
                            ranges.add(new Range(second.x(), first.x()));
                        }
                    } else if (vector2s.size()==1) {
                        ranges.add(new Range(vector2s.get(0).x(), vector2s.get(0).x()));
                    } else {
                        throw new RuntimeException("Count expected to be 1 or 2 but was "+vector2s.size());
                    }
                });

                final Range finalRange = new Range(max, min);

                List<Range> sortedRanges = ranges.stream().sorted().toList();

                for (Range range : sortedRanges) {
                    if (range.getLow()>finalRange.getHigh()) {
                        missing=Vector2.builder().y(i).x(range.getLow()-1).build();
                        break;
                    }

                    if (range.getLow()<finalRange.getLow()) {
                        if (range.getLow()<min) {
                            finalRange.setLow(min);
                        } else {
                            finalRange.setLow(range.getLow());
                        }
                    }

                    if (range.getHigh()>finalRange.getHigh()) {
                        if (range.getHigh()>max) {
                            finalRange.setHigh(max);
                        } else {
                            finalRange.setHigh(range.getHigh());
                        }
                    }

                }

            }

            return (missing.x()*4000000)+missing.y();
        };
    }

    private void addSensorsAndBeacons(List<SensorBeaconPair> sensorBeaconPairs, Map<Vector2,
        Character> output, long currentRow) {
        sensorBeaconPairs.forEach(sensorBeaconPair -> {
            if (sensorBeaconPair.getBeacon().y()==currentRow) {
                output.putIfAbsent(sensorBeaconPair.getBeacon(), 'B');
            }
            if (sensorBeaconPair.getSensor().y()==currentRow) {
                output.putIfAbsent(sensorBeaconPair.getSensor(), 'S');
            }
        });
    }

    private List<SensorBeaconPair> getInputMap(Stream<String> input) {
        final String regex = "Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)";
        final Pattern pattern = Pattern.compile(regex);

        return input.map(s -> {
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                Vector2 sensor = getVector(matcher.group(1),matcher.group(2));
                Vector2 beacon = getVector(matcher.group(3),matcher.group(4));
                return SensorBeaconPair.builder().sensor(sensor).beacon(beacon).build();
            } else {
                throw new RuntimeException();
            }
        }).toList();

    }

    private Vector2 getVector(String x, String y) {
        return Vector2.builder().x(toInt(x)).y(toInt(y)).build();
    }
    private int toInt(String s){
        return Integer.parseInt(s);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Range implements Comparable<Range>{
        private long low;
        private long high;


        @Override
        public int compareTo(Range o) {
            return Comparator.comparing(Range::getLow)
                .thenComparing(Range::getHigh).compare(this, o);
        }
    }


}
