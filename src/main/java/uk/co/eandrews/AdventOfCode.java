package uk.co.eandrews;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.co.eandrews.util.Runner;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class AdventOfCode implements ApplicationRunner {

    private final Runner runner;

    public static void main(final String[] args) {
        SpringApplication.run(AdventOfCode.class, args);
    }

    @Override
    public void run(final ApplicationArguments args) {
        runner.run();
    }
}