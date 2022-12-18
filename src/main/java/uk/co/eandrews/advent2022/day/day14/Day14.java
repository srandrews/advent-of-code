package uk.co.eandrews.advent2022.day.day14;

import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.Vector2;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Component("Day14-2022")
public class Day14 extends Day2022<Stream<String>, Long> {

    public Day14(final InputParser<Stream<String>> inputParser) {
        super(14, inputParser);
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
            List<List<Vector2>> rockPaths = getRockPaths(input);
            char[][] layout = getRockLayout(rockPaths, false);

            int sand = pourSand(layout, Vector2.builder().y(0).x(500).build());
         //   printGrid(layout);
            return Long.valueOf(sand);
        };
    }

    public PuzzleSolution<Stream<String>, Long> partTwoSolution() {
        return input -> {
            List<List<Vector2>> rockPaths = getRockPaths(input);
            char[][] layout = getRockLayout(rockPaths, true);
            int sand = pourSand(layout, Vector2.builder().y(0).x(500).build());
           //printGrid(layout);
            return Long.valueOf(sand);
        };
    }

    private void printGrid(char[][] layout) {
        for (char[] chars : layout) {
            for (int j = 328; j < 673; j++) {
                System.out.print(chars[j]);
            }
            System.out.println();
        }
    }

    private int pourSand(char[][] layout, Vector2 startPos) {

        int grainsOfSand = 0;
        boolean sandFull = false;

        while (!sandFull) {
            boolean moving = true;
            Vector2 nextPos = startPos.up(1);
            Vector2 currentPos = startPos;

            char c = layout[(int) currentPos.y()][(int) currentPos.x()];
            if (c!=' ') {
                break;
            }

            while (moving) {
                if (nextPos.y()>=layout.length) {
                    sandFull=true;
                    break;
                }
                c = layout[(int) nextPos.y()][(int) nextPos.x()];
                if (c != ' ') {
                    Vector2 possibleMove = moveDiagonal(layout, currentPos);
                    if (possibleMove.equals(currentPos)) {
                        grainsOfSand++;
                        layout[(int) currentPos.y()][(int) currentPos.x()] = 'o';
                        moving = false;
                    } else {
                        currentPos = possibleMove;
                    }
                } else {
                    currentPos = currentPos.up(1);
                }

                nextPos = nextPos.up(1).toBuilder().x(currentPos.x()).build();

            }
        }
        return grainsOfSand;
    }

    private Vector2 moveDiagonal(char[][] layout, Vector2 startPos) {
        Vector2 downLeft = startPos.up(1).left(1);
        if (layout[(int)downLeft.y()][(int)downLeft.x()] == ' ') {
            return downLeft;
        }

        Vector2 downRight = startPos.up(1).right(1);
        if (layout[(int)downRight.y()][(int)downRight.x()] == ' ') {
            return downRight;
        }

        return startPos;
    }

    private List<List<Vector2>> getRockPaths(Stream<String> input) {
        return input
            .map(s -> StreamEx.of(Arrays.asList(s.split(" -> ")))
                    .map(coords -> {
                        String[] coordsArray = coords.split(",");
                        int x = Integer.parseInt(coordsArray[0]);
                        int y = Integer.parseInt(coordsArray[1]);
                        return Vector2.builder().x(x).y(y).build();
                    })
                .toList())
            .toList();
    }

    private char[][] getRockLayout(List<List<Vector2>> rockPaths, boolean addFloor) {
        long maxY = 0;
        long maxX = 0;

        for (List<Vector2> rockPath : rockPaths) {
            for (Vector2 v : rockPath) {
                if (v.x() > maxX) {
                    maxX = v.x();
                }
                if (v.y() > maxY) {
                    maxY = v.y();
                }
            }
        }

        if (addFloor) {
            maxY = maxY+2;
            maxX = 500 + maxY*2;
        }

        char[][] grid = new char[(int)maxY+1][(int)maxX+1];

        for (char[] row: grid) {
            Arrays.fill(row, ' ');
        }

        if (addFloor) {
            for (int i=0; i<maxX; i++) {
                grid[(int)maxY][i]='#';
            }
        }

        for (List<Vector2> rockPath : rockPaths) {
            for (int i=0; i<rockPath.size()-1; i++) {
                Vector2 firstPoint = rockPath.get(i);
                Vector2 secondPoint = rockPath.get(i+1);
                drawLine(grid, firstPoint, secondPoint);
            }
        }

        return grid;
    }

    private void drawLine(char[][] grid, Vector2 firstPoint, Vector2 secondPoint) {
        if (firstPoint.x()==secondPoint.x()) {
            if (firstPoint.y()<secondPoint.y()) {
                for (int i=(int)firstPoint.y(); i<=(int)secondPoint.y(); i++) {
                    grid[i][(int)firstPoint.x()] = '#';
                }
            } else {
                for (int i=(int)secondPoint.y(); i<=(int)firstPoint.y(); i++) {
                    grid[i][(int)firstPoint.x()] = '#';
                }
            }
        } else {
            if (firstPoint.x()<secondPoint.x()) {
                for (int i=(int)firstPoint.x(); i<=(int)secondPoint.x(); i++) {
                    grid[(int)firstPoint.y()][i] = '#';
                }
            } else {
                for (int i=(int)secondPoint.x(); i<=(int)firstPoint.x(); i++) {
                    grid[(int)firstPoint.y()][i] = '#';
                }
            }
        }
    }

}
