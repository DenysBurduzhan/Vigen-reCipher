package Cipher;



import Dictionary.Dictionary;
import FileManager.FileOptions;

import java.io.IOException;
import java.nio.file.Path;

import static CyphersOptions.BruteForce.bruteforce;
import static CyphersOptions.Decryptor.decrypt;
import static CyphersOptions.Encryptor.encrypt;


public class Cipher {

     static void main(String[] args) throws IOException {
         String key = args[1];
         String word = args[2];
         FileOptions fileOptions = new FileOptions();

         if("-e".equals(args[0])){
             fileOptions.write(Path.of("output.txt" + "[ENCRYPTED]"),encrypt(key, fileOptions.read(Path.of(args[2]))));
         }else if("-d".equals(args[0])){
             fileOptions.write(Path.of("output.txt" + "[DECRYPTED]"),decrypt(key, fileOptions.read(Path.of(args[2]))));
         }else if ("-b".equals(args[0])){
             String decryptedWord = bruteforce(fileOptions.read(Path.of(args[2])));
             fileOptions.write(Path.of("output.txt" +  "[DECRYPTED]"),decryptedWord);
         }
    }
}

    
