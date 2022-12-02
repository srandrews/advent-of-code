package uk.co.eandrews.advent2022.day.day2;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
public class RockPaperScissorRound {
    private final RockPaperScissors opponent;
    private final RockPaperScissors player;

    public long getPlayerScore() {
        long score = player.getScore();
        score += opponent.getOutcome(player).getScore();
        return score;
    }
}
