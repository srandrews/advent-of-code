package uk.co.eandrews.advent2022.day.day2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum RockPaperScissors {
    ROCK("A", "X", 1),
    PAPER("B", "Y", 2),
    SCISSORS("C", "Z", 3);


    private final String opponentMove;
    private final String playerMove;
    private final int score;

    public static RockPaperScissors opponentValueOf(String played) {
       return Arrays.stream(RockPaperScissors.values())
           .filter(rockPaperScissors -> rockPaperScissors.getOpponentMove().equals(played))
           .findFirst().orElseThrow();
    }

    public static RockPaperScissors playerValueOf(String played) {
        return Arrays.stream(RockPaperScissors.values())
            .filter(rockPaperScissors -> rockPaperScissors.getPlayerMove().equals(played))
            .findFirst().orElseThrow();
    }

    public static RockPaperScissors playerOutcomeValueOf(String opponentPlayed, String outcome) {
        RockPaperScissors opponent = opponentValueOf(opponentPlayed);
        return getGoBasedOnOutcome(opponent, Outcome.getOutcome(outcome));
    }

    private static RockPaperScissors getGoBasedOnOutcome(RockPaperScissors opponent, Outcome requiredOutcome) {
        if (requiredOutcome == Outcome.DRAW) {
            return opponent;
        } else {
            if (opponent == ROCK) {
                if (requiredOutcome == Outcome.LOSE) {
                    return SCISSORS;
                } else {
                    return PAPER;
                }
            } else if (opponent == SCISSORS) {
                if (requiredOutcome == Outcome.LOSE) {
                    return PAPER;
                } else {
                    return ROCK;
                }
            } else if (opponent == PAPER) {
                if (requiredOutcome == Outcome.LOSE) {
                    return ROCK;
                } else {
                    return SCISSORS;
                }
            }
        }

        throw new IllegalArgumentException();
    }


    public Outcome getOutcome(RockPaperScissors played) {
        if (played == this) {
            return Outcome.DRAW;
        } else if (played == ROCK) {
            if (this == PAPER) {
                return Outcome.LOSE;
            } else {
                return Outcome.WIN;
            }
        } else if (played == PAPER) {
            if (this == SCISSORS) {
                return Outcome.LOSE;
            } else {
                return Outcome.WIN;
            }
        } else if (played == SCISSORS) {
            if (this == ROCK) {
                return Outcome.LOSE;
            } else {
                return Outcome.WIN;
            }

        }
        throw new IllegalArgumentException();
    }
}
