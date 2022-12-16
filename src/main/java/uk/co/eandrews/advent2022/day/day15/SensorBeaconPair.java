package uk.co.eandrews.advent2022.day.day15;

import lombok.Builder;
import lombok.Getter;
import uk.co.eandrews.util.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Getter
public class SensorBeaconPair {
    private final Vector2 sensor;
    private final Vector2 beacon;

    public boolean radiusIntersectsRow(long row) {

        long radius = getManhattanDistance(sensor, beacon);

        if (sensor.y()>=row) {
            Vector2 towardsRow = sensor.down(radius);
            return towardsRow.y() <= row;
        } else if (sensor.y()<=row) {
            Vector2 towardsRow = sensor.up(radius);
            return towardsRow.y() >= row;
        }
        return true;
    }

    public Map<Vector2, Character> pointsOnRow(long row, int maxX, int minX) {
        long radius = getManhattanDistance(sensor, beacon);

        Map<Vector2, Character> output = new HashMap<>();

        Vector2 onRow = sensor.toBuilder().y(row).build();

        long distanceToRow = getManhattanDistance(sensor, onRow);

        if (distanceToRow<=radius) {
            output.put(onRow,'#');
            for (int i = 1; i <= radius - distanceToRow; i++) {
                Vector2 left = onRow.left(i);
                if (left.x()>=minX && left.x()<=maxX) {
                    output.put(left, '#');
                }

                Vector2 right = onRow.right(i);
                if (right.x()>=minX && right.x()<=maxX) {
                    output.put(right,'#');
                }

            }
        }

        return output;
    }

    public List<Vector2> startAndEndpointsOnRow(long row, int maxX, int minX) {
        long radius = getManhattanDistance(sensor, beacon);

        List<Vector2> output = new ArrayList<>();

        Vector2 onRow = sensor.toBuilder().y(row).build();

        long distanceToRow = getManhattanDistance(sensor, onRow);

        if (distanceToRow==radius) {
            output.add(onRow);
        } else {
            output.add(onRow.left(radius - distanceToRow));
            output.add(onRow.right(radius - distanceToRow));
        }

        return output;
    }

    public Map<Vector2, Character> pointsOnRow(long row) {
        return pointsOnRow(row, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private long getManhattanDistance(Vector2 sensor, Vector2 beacon) {
        return Math.abs(sensor.x() - beacon.x())
            + Math.abs(sensor.y() - beacon.y());
    }
}
