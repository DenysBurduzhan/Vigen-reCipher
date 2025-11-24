package Cipher;


import Constants.Constants;
import Dictionary.Dictionary;

import java.util.ArrayList;
import java.util.Scanner;


public class Cipher {
   // static Scanner scan = new Scanner(System.in);
   // static String key = scan.nextLine();
    //static String word = scan.nextLine();

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

    public static char[] keyToWordLength(String key, String word) {
        char[] str = new char[word.length()];
        int keyIndex = 0;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (currentChar == ' ') {
                str[i] = ' ';
                continue;
            }
            str[i] = key.charAt(keyIndex % key.length());
            keyIndex++;
        }

        return str;

    }

    public static String decrypt(String key, String encryptedWord) {
        char[] newKey = keyToWordLength(key, encryptedWord);
        StringBuilder builder = new StringBuilder();
        int indexSet = 0;
        for (int i = 0; i < encryptedWord.length(); i++) {
            char current = encryptedWord.charAt(i);
            if(Character.isWhitespace(current)){
                builder.append(current);
                continue;
            }
            int index1 = findIndex(Constants.getEng(), newKey[i]);
            int index2 = findIndex(Constants.getEng(), encryptedWord.charAt(i));
            indexSet += index2 - index1;
            if (indexSet < 0) {
                indexSet = indexSet + 26;
                builder.append(Constants.getEng().get(indexSet));
                indexSet = 0;
            } else {
                builder.append(Constants.getEng().get(indexSet));
                indexSet = 0;
            }
        }

        return builder.toString();
    }

    public static String encrypt(String key, String word) {
        // lemon //were in fact two important
        String regex = "^[A-Za-zА-Яа-яЁё\\s]+$";
        char[] newKey = keyToWordLength(key, word);
        StringBuilder builder = new StringBuilder();
        int indexSum = 0;
        for (int i = 0; i < word.length(); i++) {
            if(!word.matches(regex)){
                throw new RuntimeException("Included symbols can't be applied");
            }
            char current = word.charAt(i);
            if(Character.isWhitespace(current)){
                builder.append(current);
                continue;
            }
            int index1 = findIndex(Constants.getEng(), newKey[i]);
            int index2 = findIndex(Constants.getEng(), word.charAt(i));
            indexSum += index1 + index2;
            if (indexSum > 25) {
                indexSum = indexSum % 25 - 1;
                builder.append(Constants.getEng().get(indexSum));
                indexSum = 0;
            } else {
                builder.append(Constants.getEng().get(indexSum));
                indexSum = 0;

            }
        }

        return builder.toString();
    }

    public static int findIndex(ArrayList<Character> array, char symbol) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == symbol) {
                return i;
            }
        }
        return -1;
    }

    public static String bruteforce(String encryptedWord, String[] dictionary){
        String decryptedWord = "";
        int matchesMax = 0;
        StringBuilder builder;
        for(String decrypted : dictionary){
            builder = new StringBuilder(decrypt(decrypted,encryptedWord));
            int matches = counter(String.valueOf(builder), dictionary);
            if(matches > matchesMax){
                matchesMax = matches;
                decryptedWord = String.valueOf(builder);
            }
        }


        return decryptedWord;
    }

    public static int counter(String text, String[] dictionary){
         int count = 0;
         for(String word : dictionary){
             if(text.contains(word)){
                 count++;

             }
         }

        return count;
    }
}

    
