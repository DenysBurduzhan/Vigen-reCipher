package Cipher;



import Dictionary.Dictionary;

import static CyphersOptions.BruteForce.bruteforce;
import static CyphersOptions.Decryptor.decrypt;
import static CyphersOptions.Encryptor.encrypt;


public class Cipher {

     static void main(String[] args) {
         String key = args[1];
         String word = args[2];

         if("-e".equals(args[0])){
             System.out.println(encrypt(key,word));
         }else if("-d".equals(args[0])){
             System.out.println(decrypt(key, word));
         }else if ("-b".equals(args[0])){
             System.out.println(bruteforce(word, Dictionary.getDictionary()));
         }
    }
}

    
