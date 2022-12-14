package uk.co.eandrews.advent2022.day.day13;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class EmptyPacket implements Packet {

    private static final List<Packet> EMPTY_LIST = new ArrayList<>();

    @Override
    public List<Packet> getPackets() {
        return EMPTY_LIST;
    }

    @Override
    public int compareTo(Packet o) {
        if (o instanceof EmptyPacket) {
            return 0;
        } else {
            return -1;
        }
    }

    public String toString() {
        return "";
    }
}
