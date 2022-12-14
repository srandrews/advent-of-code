package uk.co.eandrews.advent2022.day.day13;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode
public class IntegerPacket implements Packet{
    private final int packetValue;

    public IntegerPacket(int value) {
        packetValue = value;
    }

    @Override
    public List<Packet> getPackets() {
        return List.of(this);
    }

    @Override
    public int compareTo(Packet o) {
        if (o instanceof IntegerPacket integerPacket) {
            return Integer.compare(packetValue, integerPacket.getPacketValue());
        } else if (o instanceof ListPacket listPacket){
            ListPacket newListPacket = new ListPacket(List.of(this));
            return newListPacket.compareTo(listPacket);
        } else if (o instanceof EmptyPacket) {
            return 1;
        }

        return 0;
    }

    public String toString() {
        return Integer.toString(packetValue);
    }
}
