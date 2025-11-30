package CommandRunner;

import java.nio.file.Path;

public class CommandsParser {
    public RunOptions parse(String[] args){
        Commands command = null;
        String key = null;
        Path path = null;


        for (int i = 0; i < args.length; i++){
            String arg = args[i];
            if(arg.equalsIgnoreCase("-e")){
                command = Commands.ENCRYPT;
            }else if(arg.equalsIgnoreCase("-d")){
                command = Commands.DECRYPT;
            } else if (arg.equalsIgnoreCase("-b")) {
                command = Commands.BRUTEFORCE;
            } else if (arg.equalsIgnoreCase("-k")) {
                if(i + 1 < args.length){
                    key = args[++i];
                }else{
                    throw new IllegalArgumentException("Missing key");
                }
            } else if (arg.equalsIgnoreCase("-f")) {
                if(i + 1 < args.length){
                    StringBuilder filePathBuilder = new StringBuilder(args[++i]);
                    while (i + 1 < args.length && !args[i + 1].startsWith("-")){
                        filePathBuilder.append(" ").append(args[++i]);
                    }
                    path = Path.of(filePathBuilder.toString());
                }else{
                    throw new IllegalArgumentException("Missing path");
                }
            }else{
                throw new IllegalArgumentException("Unknown argument");
            }
        }
        if (command == Commands.ENCRYPT || command == Commands.DECRYPT) {
            if (key == null) {
                throw new IllegalArgumentException("Key is required for encrypt or decrypt mode");
            }
        }

        if (path == null) {
            throw new IllegalArgumentException("File path is required");
        }
        return new RunOptions(command, key, path);
    }
}
