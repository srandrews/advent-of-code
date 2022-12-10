package uk.co.eandrews.advent2022.day.day10;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

public interface Instruction {

    void apply(List<Cycle> cycles);

    @Value
    @EqualsAndHashCode
    class Noop implements Instruction {

        private static final int CYCLE_COUNT = 1;

        @Override
        public void apply(List<Cycle> cycles) {

            Cycle lastCycle;

            if (cycles.isEmpty()) {
                lastCycle = Cycle.builder().afterCycle(1).duringCycle(1).build();
            } else {
                lastCycle = cycles.get(cycles.size()-1);
            }

            for (int i=0;i<CYCLE_COUNT; i++) {
                cycles.add(lastCycle.toBuilder().duringCycle(lastCycle.getAfterCycle()).build());
            }

        }
    }

    @Value
    @EqualsAndHashCode
    class AddX implements Instruction {

        private static final int CYCLE_COUNT = 2;

        int increase;

        @Override
        public void apply(List<Cycle> cycles) {
            Cycle lastCycle;

            if (cycles.isEmpty()) {
                lastCycle = Cycle.builder().afterCycle(1).duringCycle(1).build();
            } else {
                lastCycle = cycles.get(cycles.size()-1);
            }

            for (int i=1;i<=CYCLE_COUNT; i++) {
                if (i==CYCLE_COUNT) {
                    cycles.add(lastCycle.toBuilder().duringCycle(lastCycle.getAfterCycle())
                        .afterCycle(lastCycle.getAfterCycle()+increase).build());
                } else {
                    cycles.add(lastCycle.toBuilder().duringCycle(lastCycle.getAfterCycle()).build());
                }
            }
        }
    }

}
