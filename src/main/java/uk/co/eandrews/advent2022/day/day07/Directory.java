package uk.co.eandrews.advent2022.day.day07;

import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Getter
public class Directory implements FileSize {

    private final String name;
    private final Directory parent;
    private final Map<String, Directory> directories = new HashMap<>();
    private final Map<String, File> files = new HashMap<>();

    public Directory(String name) {
        this.name = name;
        this.parent = this;
    }

    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public long size() {
        return Stream.of(directories.values(), files.values())
            .flatMap(Collection::stream)
            .mapToLong(FileSize::size)
            .sum();
    }

    public String getPath() {
        if (this.equals(parent)) {
            return name;
        } else {
            return parent.getPath()+name+"/";
        }
    }

    @Override
    public String toString() {
        return getPath() + " : " + this.size();
    }
}
