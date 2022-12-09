package uk.co.eandrews.advent2022.day.day09;

import lombok.Builder;
import uk.co.eandrews.util.Vector2;

import java.util.Set;
import java.util.stream.IntStream;

@Builder
public class Command {

    private int steps;
    private String direction;

    public void apply(RopeGrid ropeGrid, Set<Vector2> tailVisited) {

        IntStream.range(1, steps+1)
            .forEach(value -> {
                 ropeGrid.getRopes()[0] = move(ropeGrid.getRopes()[0]);
                IntStream.range(0, ropeGrid.getRopes().length-1)
                    .forEach(rope -> {
                        tailVisited.add(ropeGrid.getRopes()[ropeGrid.getRopes().length - 1]);

                        int tailPos = rope + 1;
                        Vector2 tail = ropeGrid.getRopes()[tailPos];

                        ropeGrid.getRopes()[tailPos] = moveTail(ropeGrid.getRopes()[rope], tail);

                        tailVisited.add(ropeGrid.getRopes()[ropeGrid.getRopes().length - 1]);
                    });
            });
    }

    private Vector2 move(Vector2 vector){
        return switch (direction) {
            case "U" -> vector.up(1);
            case "D" -> vector.down(1);
            case "L" -> vector.left(1);
            case "R" -> vector.right(1);
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }

    private Vector2 moveTail(Vector2 head, Vector2 tail) {

        if (tail.equals(head) || (nextTo(tail,head))) {
            return tail;
        }

        int changeX = Long.signum(head.x() - tail.x());
        int changeY = Long.signum(head.y() - tail.y());

        return Vector2.builder().x(tail.x() + changeX).y(tail.y()+changeY).build();

    }

    private boolean nextTo(Vector2 tail, Vector2 head) {
        return (Math.abs(head.y()-tail.y())<=1) && (Math.abs(head.x()-tail.x())<=1);
    }

}
