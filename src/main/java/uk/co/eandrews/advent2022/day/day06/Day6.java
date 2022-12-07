package uk.co.eandrews.advent2022.day.day06;

import lombok.experimental.ExtensionMethod;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.StreamExUtil;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Component("Day6-2022")
@ExtensionMethod({StreamEx.class, StreamExUtil.class})
public class Day6 extends Day2022<Stream<String>, Long> {

    public Day6(final InputParser<Stream<String>> inputParser) {
        super(6, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Stream<String>, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Stream<String>, Long> partOneSolution() {
        return input -> input.map(s -> {
                char[] signalArray = s.toCharArray();


                for (int i=0; i<signalArray.length; i++) {
                    char[] chars = Arrays.copyOfRange(signalArray, i, i+4);

                    int[] distinct = new String(chars).chars().distinct().toArray();

                    if (distinct.length==4){
                        return i+4;
                    }
                }

                return 0;
            }).mapToLong(value -> value)
                .sum();
    }

    public PuzzleSolution<Stream<String>, Long> partTwoSolution() {
       return input ->  input.map(s -> {
                   char[] signalArray = s.toCharArray();

                   for (int i=0; i<signalArray.length; i++) {
                       char[] chars = Arrays.copyOfRange(signalArray, i, i+14);

                       int[] distinct = new String(chars).chars().distinct().toArray();

                       if (distinct.length==14){
                           return i+14;
                       }
                   }

                   return 0;
               }).mapToLong(value -> value)
               .sum();
    }


}
