package uk.co.eandrews;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.co.eandrews.day.Day;
import uk.co.eandrews.util.io.input.resolver.InputResolver;

import java.util.List;

@SpringBootApplication
@Slf4j
public class AdventOfCode2021 implements ApplicationRunner {
    private final InputResolver inputResolver;
    private final List<Day<?, ?>> days;

    public AdventOfCode2021(final InputResolver inputResolver, final List<Day<?, ?>> days) {
        this.inputResolver = inputResolver;
        this.days = days;
    }

    public static void main(final String[] args) {
        SpringApplication.run(AdventOfCode2021.class, args);
    }

    @Override
    public void run(final ApplicationArguments args) {
        days.forEach(day -> log.info("Day {}: {}", day.getDay(), day.solve(inputResolver)));
    }
}