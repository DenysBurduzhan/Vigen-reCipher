package CyphersOptions;

import Constants.Constants;
import Interfaces.Key;
import Interfaces.LangSwitcher;
import Interfaces.registerSwitcher;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
public class Decryptor implements Key, LangSwitcher, registerSwitcher {
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

        for (int i = 0; i < encryptedWord.length(); i++) {
            char current = encryptedWord.charAt(i);
            ArrayList<Character> upperOrLower = decryptor.registerCheck(language,current);
            char keyChar = newKey[i];
            if (Character.isUpperCase(current)) {
                keyChar = Character.toUpperCase(keyChar);
            } else {
                keyChar = Character.toLowerCase(keyChar);
            }
            int keyIndex = findIndex(upperOrLower, keyChar);
            int cipherIndex = findIndex(upperOrLower, current);
            if (keyIndex < 0 || cipherIndex < 0) {
                builder.append(current);
                continue;
            }
            int plainIndex = (cipherIndex - keyIndex + upperOrLower.size()) % upperOrLower.size();
            builder.append(upperOrLower.get(plainIndex));
        }

        return builder.toString();
    }
    public ArrayList<Character> registerCheck(ArrayList<Character> lang, char current) {
        ArrayList<Character> upper = new ArrayList<>(lang.size() / 2);
        ArrayList<Character> lower = new ArrayList<>(lang.size() / 2);
        for (int i = 0; i < lang.size() / 2; i++) {
            upper.add(lang.get(i));
            lower.add(lang.get(i + lang.size() / 2));
        }
        if(upper.contains(current)){
            return upper;
        }
        if(lower.contains(current)){
            return  lower;
        }
        return upper;
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
            }
        }
        System.out.println("undefined language");
        return Constants.getEng();
    }
}
