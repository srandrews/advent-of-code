package uk.co.eandrews.advent2023.day.day02;

import org.springframework.stereotype.Component;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
public class GameParser implements InputParser<Stream<Game>> {

    final Pattern gameIdPattern = Pattern.compile("Game (\\d+)", Pattern.MULTILINE);
    final Pattern ballColourPattern = Pattern.compile("(\\d+) (\\w+)", Pattern.MULTILINE);

    @Override
    public Stream<Game> parse(Stream<String> input) {
        return input
                .map(s -> s.split(":"))
                .map(strings -> {
                    Game.GameBuilder gameBuilder = Game.builder()
                            .id(getGameID(strings[0]));

                    String[] roundStrings = strings[1].split(";");
                    for (String roundString : roundStrings) {
                        gameBuilder.round(getRound(roundString));
                    }

                    return gameBuilder.build();
                });
    }

    private int getGameID(String gameString) {
        final Matcher matcher = gameIdPattern.matcher(gameString);
        matcher.find();

        return Integer.parseInt(matcher.group(1));
    }

    private Round getRound(String roundString) {
        String[] balls = roundString.split(",");
        Round.RoundBuilder roundBuilder = Round.builder();
        for (String ball : balls) {
            final Matcher matcher = ballColourPattern.matcher(ball);
            matcher.find();
            String colour = matcher.group(2);
            if ("blue".equals(colour)) {
                roundBuilder.blue(Integer.parseInt(matcher.group(1)));
            } else if ("red".equals(colour)) {
                roundBuilder.red(Integer.parseInt(matcher.group(1)));
            } else {
                roundBuilder.green(Integer.parseInt(matcher.group(1)));
            }
        }

        return roundBuilder.build();
    }

}

