package uk.co.eandrews.advent2023.day.day03;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
public class SchematicParser implements InputParser<List<Schematic>> {

    private static final String SYMBOL_CHARACTERS = "/+*!@#$%^&*()\"{}_[]|\\?/<>,=-";
    private static final String NUMBER_CHARACTERS = "0123456789";
    @Override
    public List<Schematic> parse(Stream<String> input) {

        List<Schematic> schematics = new ArrayList<>();

        input.forEach(s -> {
            StringBuilder number = new StringBuilder();
            int numberStart = -1;
            boolean processingNumber = false;

            Schematic.SchematicBuilder schematicBuilder = Schematic.builder();

            for (int i=0; i<s.length(); i++) {

                String nextCharacter = s.substring(i,i+1);

                if (processingNumber && !NUMBER_CHARACTERS.contains(nextCharacter)) {
                    processingNumber = false;
                    schematicBuilder.partDetail(PartDetail.builder()
                            .partNumber(Integer.parseInt(number.toString()))
                            .startPosition(numberStart)
                            .endPosition(i-1).build());
                    number = new StringBuilder();
                    numberStart = -1;
                }

                if (SYMBOL_CHARACTERS.contains(nextCharacter)) {
                    schematicBuilder.symbolDetail(SymbolDetail.builder()
                            .position(i)
                            .symbol(nextCharacter)
                            .build());
                } else if (NUMBER_CHARACTERS.contains(nextCharacter)) {
                    processingNumber = true;
                    if (numberStart == -1) {
                        numberStart = i;
                    }
                    number.append(nextCharacter);
                } else if (!".".equals(nextCharacter)) {
                    log.error("Unknown characher: '{}'", nextCharacter);
                }
            }

            if (processingNumber) {
                schematicBuilder.partDetail(PartDetail.builder()
                        .partNumber(Integer.parseInt(number.toString()))
                        .startPosition(numberStart)
                        .endPosition(s.length()-1).build());
            }

            schematics.add(schematicBuilder.build());
        });

        return schematics;
    }
}
