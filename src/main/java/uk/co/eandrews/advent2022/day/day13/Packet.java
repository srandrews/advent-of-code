package uk.co.eandrews.advent2022.day.day13;

import java.util.List;

public interface Packet extends Comparable<Packet> {
    List<Packet> getPackets();
}
