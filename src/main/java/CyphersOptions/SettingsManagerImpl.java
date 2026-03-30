package CyphersOptions;

import Constants.Constants;
import Interfaces.FindIndex;
import Interfaces.Key;
import Interfaces.LangSwitcher;
import Interfaces.registerSwitcher;

import java.util.ArrayList;

public class SettingsManagerImpl implements registerSwitcher, FindIndex, Key, LangSwitcher {
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
}
