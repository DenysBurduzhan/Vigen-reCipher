package CyphersOptions;

import Constants.Constants;
import Interfaces.Key;
import Interfaces.LangSwitcher;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
public class Decryptor implements Key, LangSwitcher {
    public String key;
    public String encryptedWord;

    public Decryptor(String key, String encryptedWord) {
        this.key = key;
        this.encryptedWord = encryptedWord;
    }

    public static String decrypt(String key, String encryptedWord) {
        Decryptor decryptor = new Decryptor(key, encryptedWord);
        char[] newKey = decryptor.keyToWordLength(key, encryptedWord);
        StringBuilder builder = new StringBuilder();

        ArrayList<Character> language = decryptor.getLanguage(encryptedWord);
        int lengthOfLang = language.size();

        for (int i = 0; i < encryptedWord.length(); i++) {
            char current = encryptedWord.charAt(i);
            if (Character.isWhitespace(current)) {
                builder.append(current);
                continue;
            }
            int keyIndex = findIndex(language, newKey[i]);
            int cipherIndex = findIndex(language, current);
            if (keyIndex < 0 || cipherIndex < 0) {
                builder.append(current);
                continue;
            }
            int plainIndex = (cipherIndex - keyIndex + lengthOfLang) % lengthOfLang;
            builder.append(language.get(plainIndex));
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
                str[i] = currentChar;
                continue;
            }
            str[i] = key.charAt(keyIndex % key.length());
            keyIndex++;
        }

        return str;
    }
    @Override
    public ArrayList<Character> getLanguage(String text) {
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (Constants.getEng().contains(symbol)) {
                return Constants.getEng();
            } else if (Constants.getUkr().contains(symbol)) {
                return Constants.getUkr();
            } else {
                System.out.println("undefined language");
            }
        }
        return null;
    }
}
