package BruteForce;

import BruteForce.interfaces.DictionaryByLanguage;
import BruteForce.interfaces.KeyFinder;
import BruteForce.interfaces.MatchesCounter;
import Constants.Constants;
import Dictionary.Dictionary;

public class BruteForceImpl implements DictionaryByLanguage, KeyFinder, MatchesCounter {

    @Override
    public String[] getLanguage(String text) {
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (Constants.getEng().contains(symbol)) {
                return Dictionary.getDictionary();
            }
            if (Constants.getUkr().contains(symbol)) {
                return Dictionary.getDictionaryUKR();
            }
        }
        System.out.println("undefined language");
        return Dictionary.getDictionary();
    }

    @Override
    public String findKey(String encryptedWord) {
        String[] array = encryptedWord.split(" ");
        String key = null;
        for (int i = 0; i < encryptedWord.length(); i++){
            if(i == 1){
                key = array[i];
                break;
            }
        }
        return key;
    }

    @Override
    public int counter(String text, String[] dictionary) {
        int count = 0;
        for(String word : dictionary){
            if(text.contains(word)){
                count++;

            }
        }

        return count;
    }
}
