package uk.co.eandrews.advent2022.day;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.Runner;
import uk.co.eandrews.util.io.input.resolver.InputResolver;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
@Profile("2022")
public class Runner2022 implements Runner {

    private final InputResolver inputResolver;
    private final List<Day2022<?, ?>> days;

    @Override
    public void run() {
        days.forEach(day -> log.info("Day {}: {}", day.getDay(), day.solve(inputResolver)));
    }
}
