package uk.co.eandrews.advent2022;

import org.junit.jupiter.api.Test;
import uk.co.eandrews.advent2022.day.day15.Day15;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day15Test {

    String input = """
        Sensor at x=2, y=18: closest beacon is at x=-2, y=15
        Sensor at x=9, y=16: closest beacon is at x=10, y=16
        Sensor at x=13, y=2: closest beacon is at x=15, y=3
        Sensor at x=12, y=14: closest beacon is at x=10, y=16
        Sensor at x=10, y=20: closest beacon is at x=10, y=16
        Sensor at x=14, y=17: closest beacon is at x=10, y=16
        Sensor at x=8, y=7: closest beacon is at x=2, y=10
        Sensor at x=2, y=0: closest beacon is at x=2, y=10
        Sensor at x=0, y=11: closest beacon is at x=2, y=10
        Sensor at x=20, y=14: closest beacon is at x=25, y=17
        Sensor at x=17, y=20: closest beacon is at x=21, y=22
        Sensor at x=16, y=7: closest beacon is at x=15, y=3
        Sensor at x=14, y=3: closest beacon is at x=15, y=3
        Sensor at x=20, y=1: closest beacon is at x=15, y=3
        """;

    private final InputParser<Stream<String>> parser = inputResolver -> input.lines();

    @Test
    void partOneSolution_should_return_26() {
        final Day15 day15 = new Day15(parser);
        day15.setRow(10);
        assertThat(day15.partOneSolution().solve(parser.parse(input.lines()))).isEqualTo(26);
    }

    @Test
    void partTwoSolution_should_return_56000011() {
        final Day15 day15 = new Day15(parser);
        day15.setRow(10);
        day15.setMax(20);
        day15.setMin(0);
        assertThat(day15.partTwoSolution().solve(parser.parse(input.lines()))).isEqualTo(56000011L);
    }



    @Test
    void partTwoSolution_should_return_BIG() {

        String inputReal = """
            Sensor at x=489739, y=1144461: closest beacon is at x=-46516, y=554951
            Sensor at x=2543342, y=3938: closest beacon is at x=2646619, y=229757
            Sensor at x=3182359, y=3999986: closest beacon is at x=3142235, y=3956791
            Sensor at x=3828004, y=1282262: closest beacon is at x=3199543, y=2310713
            Sensor at x=871967, y=3962966: closest beacon is at x=-323662, y=4519876
            Sensor at x=1323641, y=2986163: closest beacon is at x=2428372, y=3303736
            Sensor at x=2911492, y=2576579: closest beacon is at x=3022758, y=2461675
            Sensor at x=3030965, y=2469848: closest beacon is at x=3022758, y=2461675
            Sensor at x=3299037, y=3402462: closest beacon is at x=3142235, y=3956791
            Sensor at x=1975203, y=1672969: closest beacon is at x=1785046, y=2000000
            Sensor at x=3048950, y=2452864: closest beacon is at x=3022758, y=2461675
            Sensor at x=336773, y=2518242: closest beacon is at x=1785046, y=2000000
            Sensor at x=1513936, y=574443: closest beacon is at x=2646619, y=229757
            Sensor at x=3222440, y=2801189: closest beacon is at x=3199543, y=2310713
            Sensor at x=2838327, y=2122421: closest beacon is at x=2630338, y=2304286
            Sensor at x=2291940, y=2502068: closest beacon is at x=2630338, y=2304286
            Sensor at x=2743173, y=3608337: closest beacon is at x=2428372, y=3303736
            Sensor at x=3031202, y=2452943: closest beacon is at x=3022758, y=2461675
            Sensor at x=3120226, y=3998439: closest beacon is at x=3142235, y=3956791
            Sensor at x=2234247, y=3996367: closest beacon is at x=2428372, y=3303736
            Sensor at x=593197, y=548: closest beacon is at x=-46516, y=554951
            Sensor at x=2612034, y=2832157: closest beacon is at x=2630338, y=2304286
            Sensor at x=3088807, y=3929947: closest beacon is at x=3142235, y=3956791
            Sensor at x=2022834, y=2212455: closest beacon is at x=1785046, y=2000000
            Sensor at x=3129783, y=3975610: closest beacon is at x=3142235, y=3956791
            Sensor at x=3150025, y=2333166: closest beacon is at x=3199543, y=2310713
            Sensor at x=3118715, y=2376161: closest beacon is at x=3199543, y=2310713
            Sensor at x=3951193, y=3181929: closest beacon is at x=4344952, y=3106256
            Sensor at x=2807831, y=2401551: closest beacon is at x=2630338, y=2304286
            Sensor at x=3683864, y=2906786: closest beacon is at x=4344952, y=3106256
            Sensor at x=2723234, y=3206978: closest beacon is at x=2428372, y=3303736
            Sensor at x=3047123, y=3891244: closest beacon is at x=3142235, y=3956791
            Sensor at x=3621967, y=3793314: closest beacon is at x=3142235, y=3956791
            Sensor at x=2384506, y=1814055: closest beacon is at x=2630338, y=2304286
            Sensor at x=83227, y=330275: closest beacon is at x=-46516, y=554951
            Sensor at x=3343176, y=75114: closest beacon is at x=2646619, y=229757
            """;
        InputParser<Stream<String>> parser = inputResolver -> inputReal.lines();

        final Day15 day15 = new Day15(parser);
        day15.setRow(2000000);
        day15.setMax(4000000);
        day15.setMin(0);
        assertThat(day15.partTwoSolution().solve(parser.parse(inputReal.lines()))).isEqualTo(56000011L);
    }

}
