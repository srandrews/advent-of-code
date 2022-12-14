package uk.co.eandrews.util;

import uk.co.eandrews.advent2022.day.day13.Packet;

import java.util.Collection;

/**
 * @author Gili Tzabari
 */
public final class Comparators
{
    /**
     * Verify that a comparator is transitive.
     *
     * @param <T>        the type being compared
     * @param elements   the elements to test against
     * @throws AssertionError if the comparator is not transitive
     */
    public static <T extends Packet> void verifyTransitivity(Collection<T> elements)
    {
        for (T first: elements)
        {
            for (T second: elements)
            {
                int result1 = first.compareTo(second);
                int result2 = second.compareTo(first);
                if (result1 != -result2) {
                    // Uncomment the following line to step through the failed case
                    first.compareTo(second);
                    second.compareTo(first);
                    throw new AssertionError("compare(" + first + ", " + second + ") == " + result1 +
                        " but swapping the parameters returns " + result2);
                }
            }
        }
        for (T first: elements)
        {
            for (T second: elements)
            {
                int firstGreaterThanSecond = first.compareTo(second);
                if (firstGreaterThanSecond <= 0)
                    continue;
                for (T third: elements)
                {
                    int secondGreaterThanThird = second.compareTo(third);
                    if (secondGreaterThanThird <= 0)
                        continue;
                    int firstGreaterThanThird = first.compareTo(third);
                    if (firstGreaterThanThird <= 0)
                    {
                        // Uncomment the following line to step through the failed case
                        //comparator.compare(first, third);
                        throw new AssertionError("compare(" + first + ", " + second + ") > 0, " +
                            "compare(" + second + ", " + third + ") > 0, but compare(" + first + ", " + third + ") == " +
                            firstGreaterThanThird);
                    }
                }
            }
        }
    }

    /**
     * Prevent construction.
     */
    private Comparators()
    {
    }
}
