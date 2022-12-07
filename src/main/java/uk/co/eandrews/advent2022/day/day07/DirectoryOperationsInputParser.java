package uk.co.eandrews.advent2022.day.day07;

import org.springframework.stereotype.Component;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
public class DirectoryOperationsInputParser implements InputParser<Directory> {

    private static final String ROOT_DIRECTORY = "/";
    private static final String NAVIGATE_UP = "..";
    private static final String REGEX = "\\$\\s(\\w.*)";
    final Pattern pattern = Pattern.compile(REGEX);

    private final Directory root = new Directory(ROOT_DIRECTORY);
    private Directory currentDirectory = root;

    @Override
    public Directory parse(Stream<String> input) {
        input.forEach(s -> {
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                processCommand(matcher.group(1));
            } else {
                processFile(s);
            }
        });
        return root;
    }

    private void processCommand(String command) {
        String[] commandLine = command.split(" ");
        if ("cd".equals(commandLine[0])) {
            processCd(commandLine[1]);
        }
    }

    private void processCd(String option) {
        if (ROOT_DIRECTORY.equals(option)) {
            currentDirectory = root;
        } else if (NAVIGATE_UP.equals(option)) {
            currentDirectory = currentDirectory.getParent();
        } else {
            if (currentDirectory.getDirectories().containsKey(option)) {
                currentDirectory = currentDirectory.getDirectories().get(option);
            }
        }
    }
    private void processFile(String option) {
        String[] details = option.split(" ");

        if ("dir".equals(details[0])) {
            addDirectory(details[1]);
        } else {
            addFile(details[1], Long.parseLong(details[0]));
        }
    }

    private void addDirectory(String name) {
        Directory dir = new Directory(name, currentDirectory);
        currentDirectory.getDirectories().put(name, dir);
    }

    private void addFile(String name, long size) {
        File f = new File(name, size);
        currentDirectory.getFiles().put(name, f);
    }
}