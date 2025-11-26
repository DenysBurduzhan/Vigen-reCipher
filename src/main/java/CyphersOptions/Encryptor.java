package CyphersOptions;

import Constants.Constants;
import Interfaces.Key;
import Interfaces.LangSwitcher;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Encryptor implements Key, LangSwitcher {
    public String key;
    public String word;

     Encryptor(String key, String word){
        this.key = key;
        this.word = word;
    }

    public static String encrypt(String key, String word) {
        // lemon //were in fact two important
        Encryptor encryptor = new Encryptor(key, word);
        String regex = "^[A-Za-zА-Яа-яЁёҐґЄєІіЇї\\s]+$";
        char[] newKey = encryptor.keyToWordLength(key, word);
        StringBuilder builder = new StringBuilder();
        ArrayList<Character> language = encryptor.getLanguage(word);
        int lengthOfLang = language.size();
        for (int i = 0; i < word.length(); i++) {
            if(!word.matches(regex)){
                throw new RuntimeException("Included symbols can't be applied");
            }
            char current = word.charAt(i);
            if(Character.isWhitespace(current)){
                builder.append(current);
                continue;
            }
            int index1 = findIndex(language, newKey[i]);
            int index2 = findIndex(language, word.charAt(i));
            int indexSum = (index1 + index2) % lengthOfLang;
            builder.append(encryptor.getLanguage(word).get(indexSum));

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
