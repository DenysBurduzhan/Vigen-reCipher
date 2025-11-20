package Cipher;


import java.util.ArrayList;
import java.util.Scanner;


public class Cipher {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String key = scan.nextLine();
        String word = scan.nextLine();

        System.out.println(encrypt(key, word));
        String encryptedWord = encrypt(key, word);
        System.out.println(decrypt(key, word, encryptedWord));
    }

    public static char[] keyToWordLength(String key, String word){
        char[] str = null;
        if (word.length() > key.length()) {
            str = new char[word.length()];
            for (int i = 0; i < key.length(); i++) {
                str[i] = key.charAt(i);
            }
            for(int f = 0; f < word.length() - key.length(); f++ ){
                str[key.length() + f] = key.charAt(f);
            }
        }
        return str;
    }
    public static String decrypt(String key, String word, String encryptedWord){
        char[] newKey = keyToWordLength(key, word);
        StringBuilder builder = new StringBuilder();
        int indexSet = 0;
        for (int i = 0; i < word.length(); i++) {
            int index1 = findIndex(Constants.getEng(), newKey[i]);
            int index2 = findIndex(Constants.getEng(), encryptedWord.charAt(i));
            indexSet += index2 - index1;
            if(indexSet < 0) {
                indexSet = indexSet + 26;
                builder.append(Constants.getEng().get(indexSet));
                indexSet = 0;
            }else{
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
            if(indexSum > 25) {
                indexSum = indexSum % 25 - 1;
                builder.append(Constants.getEng().get(indexSum));
                indexSum = 0;
            }else{
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
}
