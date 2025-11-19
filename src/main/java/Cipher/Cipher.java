package Cipher;


import java.util.ArrayList;
import java.util.Scanner;

import static Cipher.Constants.eng;

public class Cipher {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String key = scan.nextLine();
        String word = scan.nextLine();

            System.out.println(encrypt(key, word));
    }

    public static String encrypt(String key, String word) {
        // lemon helloworld

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

        StringBuilder builder = new StringBuilder();
        int indexSum = 0;
        for (int i = 0; i < word.length(); i++) {
            int index1 = findIndex(eng, str[i]);
            int index2 = findIndex(eng, word.charAt(i));
            indexSum += index1 + index2;
            if(indexSum > 25) {
                indexSum = indexSum % 25 - 1;
                builder.append(eng.get(indexSum));
                indexSum = 0;
            }else{
                builder.append(eng.get(indexSum));
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
