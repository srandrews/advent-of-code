package uk.co.eandrews.advent2022.day.day13;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PacketPair {

    private int id;
    private Packet left;
    private Packet right;

    public boolean outOfOrder() {
        int compare = left.compareTo(right);
        if (compare<0) {
            return false;
        } else return compare > 0;
    }
}
