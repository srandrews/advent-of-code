package uk.co.eandrews.advent2023.day.day01;

import com.google.common.base.CharMatcher;
import lombok.experimental.ExtensionMethod;
import one.util.streamex.LongStreamEx;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.advent2023.Day2023;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.StreamExUtil;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Component("Day1-2023")
public class Day1 extends Day2023<Stream<String>, Long> {

    public Day1(final InputParser<Stream<String>> inputParser) {
        super(1, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Stream<String>, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Stream<String>, Long> partOneSolution() {
        return input -> getTotal(input, false);
    }

    public PuzzleSolution<Stream<String>, Long> partTwoSolution() {
        return input ->  getTotal(input, true);
    }

    private Long getTotal(Stream<String> input, boolean calculateSpelled) {
        return input.map(s -> {
                if (calculateSpelled) {
                    return replaceSpeltNumbers(s);
                } else {
                    return s;
                }
            })
            .mapToLong(this::getInteger)
            .sum();

    }

    private String replaceSpeltNumbers(final String str) {

        String input = str;

        String[] numbersTest = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
                .toArray(String[]::new);
        String[] numbersReplace = List.of("o1e", "t2o", "th3ee", "fo4ur", "fi5ve", "s6x", "se7en", "ei8ht", "ni9ne")
                .toArray(String[]::new);

        boolean matchFound = true;
        String matchedNumber = "";
        int matchedIndex = 0;
        int matchedPos = input.length();
        while (matchFound == true) {
            matchFound=false;
            matchedPos = input.length();
            for (int i=0; i<numbersTest.length; i++) {
                int pos = input.indexOf(numbersTest[i]);
                if (pos>-1 && pos<matchedPos) {
                    matchedIndex=i;
                    matchedPos=pos;
                    matchedNumber=numbersTest[i];
                    matchFound=true;
                }
            }
            if (matchFound) {
                input = input.replaceFirst(matchedNumber, numbersReplace[matchedIndex]);
            }
        }

        return input;

    }



    private int getInteger(String str) {
        String theDigits = CharMatcher.inRange('0', '9').retainFrom(str);
        String twoDigitNumber = "" + theDigits.charAt(0) + theDigits.charAt(theDigits.length()-1);
        return Integer.parseInt(twoDigitNumber);
    }
}
