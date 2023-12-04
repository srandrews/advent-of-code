package uk.co.eandrews.advent2023.day.day04;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2023.Day2023;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("Day4-2023")
public class Day4 extends Day2023<Stream<String>, Long> {

    public Day4(final InputParser<Stream<String>> inputParser) {
        super(4, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Stream<String>, Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Stream<String>, Long> partOneSolution() {
        return input -> getTickets(input)
                .values()
                .stream()
                .mapToLong(Ticket::getNumberOfWinners)
                .map(l -> {
                    if (l==0) {
                        return 0;
                    } else {
                        return (long) Math.pow(2, (l - 1));
                    }
                })
                .sum();
    }

    public PuzzleSolution<Stream<String>, Long> partTwoSolution() {
        return input -> {
            Map<Integer, Ticket> ticketMap = getTickets(input);
            ticketMap.values().stream()
                    .forEach(ticket -> {
                        int winners = ticket.getNumberOfWinners();
                        for (int i = 0; i<winners; i++) {
                            ticketMap.get(i+ticket.getTicketId()+1).addTicket(ticket.getOwned());
                        }
                    });

            return ticketMap.values().stream().mapToLong(Ticket::getOwned).sum();
        };
    }

    private Map<Integer, Ticket> getTickets(Stream<String> input) {
        return input.map(s -> s.split(":"))
                .map(strings -> {
                    Ticket.TicketBuilder builder = Ticket.builder();
                    builder.ticketId(Integer.parseInt(strings[0].trim().replaceAll(" +", " ")
                            .split(" ")[1]));
                    String[] numbers = strings[1].split("\\|");

                    builder.winningNumbers(Arrays.stream(numbers[0].split(" "))
                            .filter(s -> !s.isEmpty())
                            .map(Long::parseLong)
                            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll));

                    builder.ticketNumbers(Arrays.stream(numbers[1].split(" "))
                            .filter(s -> !s.isEmpty())
                            .map(Long::parseLong)
                            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll));

                    builder.owned(1);

                    return builder.build();
                })
                .collect(Collectors.toMap(Ticket::getTicketId, ticket -> ticket));

    }

}
