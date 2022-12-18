package uk.co.eandrews.advent2022.day.day16;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder(toBuilder = true)
@Getter
public class ValveOpeningTimes {
    private Map<Valve, Integer> valveTimes;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (o instanceof ValveOpeningTimes v) {
            return this.getValveTimes().equals(v.getValveTimes());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return valveTimes.hashCode();
    }

}
