package Cipher;



import CommandRunner.Commands;
import CommandRunner.CommandsParser;
import CommandRunner.RunOptions;
import CyphersOptions.BruteForce;
import Dictionary.Dictionary;
import FileManager.FileOptions;

import java.io.IOException;
import java.nio.file.Path;

import static CyphersOptions.BruteForce.bruteforce;
import static CyphersOptions.Decryptor.decrypt;
import static CyphersOptions.Encryptor.encrypt;


public class Cipher {
    public static void main(String[] args) throws IOException {
        FileOptions fileOptions = new FileOptions();
        CommandsParser commandsParser = new CommandsParser();
        RunOptions runOptions = commandsParser.parse(args);

        if(runOptions.getCommands() == Commands.ENCRYPT){
            fileOptions.write(Path.of("output[ENCRYPTED].txt"),
                    encrypt(runOptions.getKey(), fileOptions.read(runOptions.getFilePath())));
        } else if(runOptions.getCommands() == Commands.DECRYPT){
            fileOptions.write(Path.of("output[DECRYPTED].txt"),
                    decrypt(runOptions.getKey(), fileOptions.read(runOptions.getFilePath())));
        } else if(runOptions.getCommands() == Commands.BRUTEFORCE){
            String decryptedWord = bruteforce(fileOptions.read(runOptions.getFilePath()));
            fileOptions.write(Path.of("output[DECRYPTED]" + BruteForce.findKey(decryptedWord) + ".txt"),
                    decryptedWord);
        }
    }
}

    
