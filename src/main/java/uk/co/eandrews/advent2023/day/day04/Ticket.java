package uk.co.eandrews.advent2023.day.day04;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Getter
public class Ticket {
    private int owned;
    private final int ticketId;
    private final List<Long> winningNumbers;
    private final List<Long> ticketNumbers;

    public void addTicket(int qty) {
        owned += qty;
    }

    public int getNumberOfWinners() {
        Collection<Long> myTicketNumbers = new ArrayList<>(ticketNumbers);
        myTicketNumbers.retainAll(winningNumbers);
        return myTicketNumbers.size();
    }
}
