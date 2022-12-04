package uk.co.eandrews.advent2022.day.day02;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
public class RockPaperScissorRound {
    private final RockPaperScissors opponent;
    private final RockPaperScissors player;

    private final RockPaperScissors outcome;

    public long getPlayerScore() {
        long score = player.getScore();
        score += opponent.getOutcome(player).getScore();
        return score;
    }

    public long getPlayerScoreOutcome() {
        long score = outcome.getScore();
        score += opponent.getOutcome(outcome).getScore();
        return score;
    }
}
