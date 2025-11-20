package Cipher;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Cipher {
    static Scanner scan = new Scanner(System.in);
    static String key = scan.nextLine();
    static String word = scan.nextLine();

     static void main(String[] args) {


        System.out.println(encrypt(key, word));
        String encryptedWord = encrypt(key, word);
        System.out.println(decrypt(key, encryptedWord));
        System.out.println(bruteforce(encryptedWord));
    }

    public static char[] keyToWordLength(String key, String word) {
            char[] str = new char[word.length()];
            for (int i = 0; i < word.length(); i++) {
                str[i] = key.charAt(i % key.length());
            }
            return str;
        }

    public static String decrypt(String key, String encryptedWord) {
        char[] newKey = keyToWordLength(key, encryptedWord);
        StringBuilder builder = new StringBuilder();
        int indexSet = 0;
        for (int i = 0; i < encryptedWord.length(); i++) {
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
        // lemon helloworld
        char[] newKey = keyToWordLength(key, word);
        StringBuilder builder = new StringBuilder();
        int indexSum = 0;
        for (int i = 0; i < word.length(); i++) {
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
    public static String bruteforce(String encryptedWord){
        String key ;
        String decryptedWord = "";
        for(int i = 0; i < Constants.getDictionary().size(); i++){
            key = (Constants.getDictionary().get(i));
            char[] newKey = Cipher.keyToWordLength(String.valueOf(key), encryptedWord);
            decryptedWord = decrypt(String.valueOf(newKey), encryptedWord);
            System.out.println(decryptedWord);


        }

        return decryptedWord;
    }
}

    
