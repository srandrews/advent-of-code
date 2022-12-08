package uk.co.eandrews.advent2022.day.day08;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Component;

import one.util.streamex.StreamEx;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.StreamExUtil;
import uk.co.eandrews.util.io.input.parser.InputParser;

@Component("Da87-2022")
@ExtensionMethod({StreamEx.class, StreamExUtil.class})
public class Day8 extends Day2022<Integer[][], Long> {

    public Day8(final InputParser<Integer[][]> inputParser) {
        super(8, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Integer[][], Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Integer[][], Long> partOneSolution() {
        return input -> {

            Set<String> visible = new HashSet<>();

            int maxHeight=0;

            for (int i=0; i<input.length; i++) {
                for (int j = 0; j<input[i].length; j++){
                    if (input[i][j]>maxHeight) {
                        maxHeight = input[i][j];
                        visible.add("("+i+","+j+")");
                    }
                }
                maxHeight=0;
            }

            for (int i=input.length-1; i>=0; i--) {
                for (int j = 0; j<input[i].length; j++){
                    if (input[i][j]>maxHeight) {
                        maxHeight = input[i][j];
                        visible.add("("+i+","+j+")");
                    }
                }
                maxHeight=0;
            }

            for (int i=0; i<input.length; i++) {
                for (int j=input[i].length-1; j>=0; j--) {
                    if (input[i][j]>maxHeight) {
                        maxHeight = input[i][j];
                        visible.add("("+i+","+j+")");
                    }
                }
                maxHeight=0;
            }

            for (int i=input.length-1; i>=0; i--) {
                for (int j=input[i].length-1; j>=0; j--) {
                    if (input[i][j]>maxHeight) {
                        maxHeight = input[i][j];
                        visible.add("("+i+","+j+")");
                    }
                }
                maxHeight=0;
            }

            return Long.valueOf(visible.size());
        };
    }

    public PuzzleSolution<Integer[][], Long> partTwoSolution() {
        return input -> 0L;
    }

}
