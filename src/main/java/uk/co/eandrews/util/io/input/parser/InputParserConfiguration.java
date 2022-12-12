package uk.co.eandrews.util.io.input.parser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Configuration
public class InputParserConfiguration {
    @Bean
    public InputParser<Stream<String>> stringStreamInputParser() {
        return input -> input;
    }

    @Bean
    public InputParser<IntStream> intStreamInputParser() {
        return input -> input.mapToInt(Integer::parseInt);
    }

    @Bean
    public InputParser<LongStream> longStreamInputParser() {
        return input -> input.mapToLong(Long::parseLong);
    }

    @Bean
    public InputParser<char[][]> charArrayStreamInputParser() {
        return input -> input.map(String::toCharArray).toArray(char[][]::new);
    }

    @Bean
    public InputParser<DoubleStream> doubleStreamInputParser() {
        return input -> input.mapToDouble(Double::parseDouble);
    }
}
