package CommandRunner;

import lombok.Getter;

import java.nio.file.Path;

@Getter
public class RunOptions {
    private final Commands commands;
    private final String key;
    private final Path filePath;

    public RunOptions(Commands commands, String key, Path filePath) {
        this.commands = commands;
        this.key = key;
        this.filePath = filePath;
    }
}
