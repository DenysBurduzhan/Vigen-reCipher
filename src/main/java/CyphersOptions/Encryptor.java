package CyphersOptions;

import Constants.Constants;
import Interfaces.Key;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Encryptor implements Key {
    public String key;
    public String word;

     Encryptor(String key, String word){
        this.key = key;
        this.word = word;
    }
    public static String encrypt(String key, String word) {
        // lemon //were in fact two important
        String regex = "^[A-Za-zА-Яа-яЁё\\s]+$";
        char[] newKey = new Encryptor(key, word).keyToWordLength(key, word);
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

    @Override
    public char[] keyToWordLength(String key, String word) {
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
}
