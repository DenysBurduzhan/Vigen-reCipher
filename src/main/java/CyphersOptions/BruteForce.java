package CyphersOptions;

import Constants.Constants;
import Dictionary.Dictionary;


import static CyphersOptions.Decryptor.decrypt;

public class BruteForce {
    public String encryptedWord;

    BruteForce(String encryptedWord ){
        this.encryptedWord = encryptedWord;
    }
    public static String bruteforce(String encryptedWord){
        String decryptedWord = "";
        int matchesMax = 0;
        String key = null;
        StringBuilder builder;
        BruteForce bruteForce = new BruteForce(encryptedWord);
        String[] dictionary = bruteForce.getLanguage(encryptedWord);
        for(String decrypted : dictionary){
            builder = new StringBuilder(decrypt(decrypted,encryptedWord));
            int matches = counter(String.valueOf(builder), dictionary);
            if(matches > matchesMax){
                matchesMax = matches;
                decryptedWord = String.valueOf(builder);
                key = decrypted;
            }
        }


        return "ключ: " + key + " text: " + decryptedWord;
    }

    public static String findKey(String encryptedWord){
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




    public static int counter(String text, String[] dictionary){
        int count = 0;
        for(String word : dictionary){
            if(text.contains(word)){
                count++;

            }
        }

        return count;
    }
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
}
