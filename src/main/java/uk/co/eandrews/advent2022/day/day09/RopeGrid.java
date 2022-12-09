package uk.co.eandrews.advent2022.day.day09;

import lombok.Getter;
import lombok.Setter;
import uk.co.eandrews.util.Vector2;

@Setter
@Getter
public class RopeGrid {

    private Vector2[] ropes;

    public RopeGrid(int ropeLength) {
        ropes = new Vector2[ropeLength];

        for (int i=0;i<ropeLength;i++){
            ropes[i] = Vector2.ZERO;
        }
    }

}
