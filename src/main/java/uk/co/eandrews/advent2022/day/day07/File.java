package uk.co.eandrews.advent2022.day.day07;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@RequiredArgsConstructor
public class File implements FileSize{
    private final String name;
    @Accessors(fluent = true)
    private final long size;

    @Override
    public String toString() {
        return name + " : " + this.size();
    }
}
