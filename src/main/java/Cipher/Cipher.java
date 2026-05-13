package Cipher;



import CommandRunner.Commands;
import CommandRunner.CommandsParser;
import CommandRunner.RunOptions;
import BruteForce.BruteForce;
import FileManager.FileOptions;
import java.io.IOException;
import java.nio.file.Path;
import static BruteForce.BruteForce.bruteforce;
import static CyphersOptions.Decryptor.decrypt;
import static CyphersOptions.Encryptor.encrypt;


public class Cipher {
    public static void main(String[] args) throws IOException {
        FileOptions fileOptions = new FileOptions();
        CommandsParser commandsParser = new CommandsParser();
        RunOptions runOptions = commandsParser.parse(args);

        try {
            if (runOptions.getCommands() == Commands.ENCRYPT) {
                String content = encrypt(runOptions.getKey(), fileOptions.read(runOptions.getFilePath()));
                String fileName = runOptions.getFilePath().getFileName().toString();
                String newFileName = fileName.substring(0, fileName.length() - 4) + " [ENCRYPTED].txt";
                Path newFilePath = runOptions.getFilePath().resolveSibling(newFileName);
                fileOptions.write(newFilePath,content);
            } else if (runOptions.getCommands() == Commands.DECRYPT) {
                String content = decrypt(runOptions.getKey(), fileOptions.read(runOptions.getFilePath()));
                String fileName = runOptions.getFilePath().getFileName().toString();
                String newFileName = fileName.substring(0, fileName.length() - 4) + " [DECRYPTED].txt";
                Path newFilePath = runOptions.getFilePath().resolveSibling(newFileName);
                fileOptions.write(newFilePath,content);
            } else if (runOptions.getCommands() == Commands.BRUTEFORCE) {
                String content = bruteforce(fileOptions.read(runOptions.getFilePath()));
                String fileName = runOptions.getFilePath().getFileName().toString();
                String newFileName = fileName.substring(0, fileName.length() - 4) + " [DECRYPTED Key -" + BruteForce.bruteForceImpl.findKey(content) + "].txt";
                Path newFilePath = runOptions.getFilePath().resolveSibling(newFileName);
                fileOptions.write(newFilePath,content);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }
}

    
