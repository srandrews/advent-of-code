package uk.co.eandrews.advent2022.day.day13;

import lombok.experimental.ExtensionMethod;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.StreamExUtil;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Component("Day13-2022")
@ExtensionMethod({StreamEx.class, StreamExUtil.class})
public class Day13 extends Day2022<Stream<String>, Long> {

    public Day13(final InputParser<Stream<String>> inputParser) {
        super(13, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Stream<String>, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Stream<String>, Long> partOneSolution() {
        return input -> getPacketPairs(input)
                .filter(packetPair -> !packetPair.outOfOrder())
                .mapToLong(PacketPair::getId)
                .sum();
    }

    public PuzzleSolution<Stream<String>, Long> partTwoSolution() {
        return input -> {

            ListPacket two = new ListPacket("[[2]]");
            ListPacket six = new ListPacket("[[6]]");
            PacketPair pair = new PacketPair(99, two, six);

            List<Packet> sortedPackets = Stream.concat(Stream.of(pair), getPacketPairs(input))
                .flatMap(packetPair -> Stream.of(packetPair.getLeft(), packetPair.getRight()))
                .sorted()
                .toList();

            long decoderKey = 1;

            for (int i=0;i<sortedPackets.size();i++) {
                if (sortedPackets.get(i).equals(two) || sortedPackets.get(i).equals(six)){
                    decoderKey = decoderKey * (i+1);
                }
            }

            return decoderKey;
        };
    }

    private StreamEx<PacketPair> getPacketPairs(Stream<String> input) {

        AtomicInteger id = new AtomicInteger();

        return input.toList().ofVariableSubLists(String::isEmpty)
            .map(strings -> {
                Packet left = new ListPacket(strings.get(0));
                Packet right = new ListPacket(strings.get(1));
                return PacketPair.builder().left(left).right(right).id(id.addAndGet(1)).build();
            });
    }
}
