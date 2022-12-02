package uk.co.eandrews.advent2022.day.day2;

import org.springframework.stereotype.Component;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

@Component
public class RockPaperScissorsInputParser implements InputParser<Stream<RockPaperScissorRound>> {
    @Override
    public Stream<RockPaperScissorRound> parse(Stream<String> input) {
        return input.map(line -> {
            final String[] round = line.split(" ");
            return RockPaperScissorRound.builder()
                .opponent(RockPaperScissors.opponentValueOf(round[0]))
                .player(RockPaperScissors.playerValueOf(round[1]))
                .outcome(RockPaperScissors.playerOutcomeValueOf(round[0], round[1]))
                .build();
        });
    }
}