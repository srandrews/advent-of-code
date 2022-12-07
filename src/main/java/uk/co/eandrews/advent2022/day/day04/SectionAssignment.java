package uk.co.eandrews.advent2022.day.day04;

import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
public class SectionAssignment {
    private final Set<Integer> elfOneAssignments;
    private final Set<Integer> elfTwoAssignments;

    public boolean fullIntersect() {
        return elfOneAssignments.containsAll(elfTwoAssignments)
            || elfTwoAssignments.containsAll(elfOneAssignments);
    }

    public boolean partialIntersect() {

        Set<Integer> elfOneAssignmentsCopy = new HashSet<>(elfOneAssignments);
        elfOneAssignmentsCopy.retainAll(elfTwoAssignments);

        return elfOneAssignmentsCopy.isEmpty();
    }
}
