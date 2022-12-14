package uk.co.eandrews.advent2022.day.day13;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class ListPacket implements Packet {
    private final List<Packet> packetList;

    public ListPacket(String values){
        packetList = getElements(values)
            .stream()
            .map(s -> {
                if (s.isEmpty()) {
                    return new EmptyPacket();
                } else {
                    try {
                        return new IntegerPacket(Integer.parseInt(s));
                    } catch (NumberFormatException nfe) {
                        return new ListPacket(s);
                    }
                }
            })
            .toList();
    }
    public ListPacket(List<Packet> packets) {
        this.packetList = packets;
    }


    @Override
    public int compareTo(Packet o) {
        if (o instanceof ListPacket listPacket) {
            for (int i=0; i<packetList.size(); i++) {
                if (i>=listPacket.getPackets().size()) {
                    return 1;
                }
                Packet p0 = packetList.get(i);
                Packet p1 = listPacket.getPackets().get(i);

                if (p0 instanceof ListPacket) {
                    if (p1 instanceof IntegerPacket) {
                        p1 = new ListPacket(List.of(p1));
                    } if (p1 instanceof EmptyPacket) {
                        return 1;
                    }
                }
                int compare = p0.compareTo(p1);
                if (compare!=0){
                    return compare;
                }
            }
            if (packetList.size()<o.getPackets().size()) {
                return -1;
            }
        } else if (o instanceof IntegerPacket intPacket) {
            ListPacket listPacket = new ListPacket(List.of(intPacket));
            return this.compareTo(listPacket);
        } else if (o instanceof EmptyPacket) {
            return 1;
        }
        return 0;
    }

    @Override
    public List<Packet> getPackets() {
        return packetList;
    }

    private List<String> getElements(String packet) {

        List<String> elements = new ArrayList<>();

        StringBuilder builder = new StringBuilder();

        int inBlocks = 0;

        for (char c : packet.toCharArray()) {
            if (c=='[') {
                inBlocks++;
                if (inBlocks>1) {
                    builder.append(c);
                }
            } else if (c==']'){
                inBlocks--;
                if (inBlocks==0) {
                    elements.add(builder.toString());
                    builder = new StringBuilder();
                } else {
                    builder.append(c);
                }
            } else if (c==',' && inBlocks==1) {
                if (builder.length()>0) {
                    elements.add(builder.toString());
                }
                builder = new StringBuilder();
            } else {
                builder.append(c);
            }
        }

        if (builder.length()>0) {
            elements.add(builder.toString());
        }

        return elements;
    }

    public String toString() {
        String s = packetList.stream()
            .map(Packet::toString)
            .collect(Collectors.joining(","));
        return "["+s+"]";
    }

}
