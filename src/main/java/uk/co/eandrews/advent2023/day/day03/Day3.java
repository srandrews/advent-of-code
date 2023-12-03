package uk.co.eandrews.advent2023.day.day03;

import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2023.Day2023;
import uk.co.eandrews.advent2023.day.day02.Game;
import uk.co.eandrews.advent2023.day.day02.Round;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.*;
import java.util.stream.Stream;

@Component("Day3-2023")
public class Day3 extends Day2023<List<Schematic>, Long> {

    public Day3(final InputParser<List<Schematic>> inputParser) {
        super(3, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<List<Schematic>, Long>> getSolutions() {
        return List.of(
                partOneSolution(),
                partTwoSolution()
        );
    }

    public PuzzleSolution<List<Schematic>, Long> partOneSolution() {
        return input -> validPartnumbers(input)
                .stream()
                .mapToLong(Integer::intValue)
                .sum();
    }

    public PuzzleSolution<List<Schematic>, Long> partTwoSolution() {
        return input -> gearRatios(input)
                .stream()
                .mapToLong(Long::longValue)
                .sum();
    }

    private Collection<Long> gearRatios(List<Schematic> schematics) {
        Collection<Long> gearRatios = new ArrayList<>();

        for (int i = 0; i<schematics.size(); i++) {

            Schematic currentSchematic = schematics.get(i);
            List<Schematic> currentSchematics = new ArrayList<>();
            currentSchematics.add(currentSchematic);
            if (i>0) {
                currentSchematics.add(schematics.get(i-1));
            }

            if (i<schematics.size()-1) {
                currentSchematics.add(schematics.get(i+1));
            }

            currentSchematic.getSymbolDetails()
                    .stream()
                    .filter(symbolDetail -> "*".equals(symbolDetail.getSymbol()))
                    .mapToLong(symbolDetail -> {

                        return getGearRatio(symbolDetail, currentSchematics);
                    })
                    .filter(value -> value!=0)
                    .forEach(gearRatios::add);
        }
        return gearRatios;
    }

    private Long getGearRatio(SymbolDetail symbolDetail, Collection<Schematic> schematics) {
        List<Long> matchingPartNumbers = schematics.stream()
                .map(Schematic::getPartDetails)
                .flatMap(Collection::stream)
                .filter(partDetail -> symbolDetail.getPosition()>= partDetail.getStartPosition()-1
                        && symbolDetail.getPosition() <= partDetail.getEndPosition()+1)
                .map(PartDetail::getPartNumber)
                .map(Integer::longValue)
                .toList();

        if (matchingPartNumbers.size()!=2) {
            return 0L;
        } else {
            return matchingPartNumbers.stream()
                    .mapToLong(Long::longValue).reduce((left, right) -> left*right).orElse(0);
        }

    }


    private Collection<Integer> validPartnumbers(List<Schematic> schematics) {
        Collection<Integer> partNumbers = new ArrayList<>();

        for (int i = 0; i<schematics.size(); i++) {
            Schematic currentSchematic = schematics.get(i);
            List<Schematic> currentSchematics = new ArrayList<>();
            currentSchematics.add(currentSchematic);

            if (i>0) {
                currentSchematics.add(schematics.get(i-1));
            }

            if (i<schematics.size()-1) {
                currentSchematics.add(schematics.get(i+1));
            }

            currentSchematic.getPartDetails()
                    .forEach(partDetail -> {
                        if (validPart(partDetail, currentSchematics)) {
                            partNumbers.add(partDetail.getPartNumber());
                        }
                    });
        }


        return partNumbers;
    }

    private boolean validPart(PartDetail partDetail, Collection<Schematic> schematics) {
        return schematics.stream()
                .map(Schematic::getSymbolDetails)
                .anyMatch(symbolDetails -> symbolDetails.stream()
                        .anyMatch(symbolDetail -> symbolDetail.getPosition()>= partDetail.getStartPosition()-1
                                && symbolDetail.getPosition() <= partDetail.getEndPosition()+1));

    }



}
