package CyphersOptions;

import Constants.Constants;
import Interfaces.*;

import java.util.ArrayList;

public class SettingsManagerImpl implements registerSwitcher, FindIndex, Key, LangSwitcher, EncryptOrDecrypt {
    @Override
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

    @Override
    public int findIndex(ArrayList<Character> array, char symbol) {
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

    @Override
    public String process(String key, String word, boolean encrypt) {
        if(key == null || key.trim().isEmpty()){
            throw new IllegalArgumentException("Missing key");
        }else if(word == null || word.trim().isEmpty()){
            throw new IllegalArgumentException("Missing word");
        }
        SettingsManagerImpl settingsManager = new SettingsManagerImpl();
        char[] newKey = settingsManager.keyToWordLength(key, word);
        StringBuilder builder = new StringBuilder();
        ArrayList<Character> language = settingsManager.getLanguage(word);

        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            ArrayList<Character> upperOrLower = settingsManager.registerCheck(language,current);
            char keyChar = newKey[i];
            keyChar = Character.isUpperCase(current) ? Character.toUpperCase(keyChar) : Character.toLowerCase(keyChar);
            int keyIndex = settingsManager.findIndex(upperOrLower, keyChar);
            int cipherIndex = settingsManager.findIndex(upperOrLower, current);
            if (keyIndex < 0 || cipherIndex < 0) {
                builder.append(current);
                continue;
            }
            if(encrypt){
                int indexSum = (keyIndex + cipherIndex) % upperOrLower.size();
                builder.append(upperOrLower.get(indexSum));
            }else {
                int plainIndex = (cipherIndex - keyIndex + upperOrLower.size()) % upperOrLower.size();
                builder.append(upperOrLower.get(plainIndex));
            }
        }

        return builder.toString();
    }
}
