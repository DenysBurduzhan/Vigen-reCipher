package CyphersOptions;

import Constants.Constants;
import Interfaces.Key;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Decryptor implements Key {
    public String key;
    public String encryptedWord;

    public Decryptor(String key, String encryptedWord) {
        this.key = key;
        this.encryptedWord = encryptedWord;
    }

    public static String decrypt(String key, String encryptedWord) {
        char[] newKey = new Decryptor(key, encryptedWord).keyToWordLength(key, encryptedWord);
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
