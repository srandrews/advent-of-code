package uk.co.eandrews.util.io.input.resolver;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class InputResolverConfiguration {

    @Bean
    @SneakyThrows
    public FileInputResolver fileInputResolver(@Value("${puzzle.input-data-path}") final String inputDataPath) {
        return new FileInputResolver(Path.of(ClassLoader.getSystemResource(inputDataPath).toURI()));
    }
}
