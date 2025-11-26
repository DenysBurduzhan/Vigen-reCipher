package CyphersOptions;

import static CyphersOptions.Decryptor.decrypt;

public class BruteForce {
    public String encryptedWord;
    public String[] dictionary;

    BruteForce(String encryptedWord, String[] dictionary ){
        this.encryptedWord = encryptedWord;
        this.dictionary = dictionary;
    }
    public static String bruteforce(String encryptedWord, String[] dictionary){
        String decryptedWord = "";
        int matchesMax = 0;
        StringBuilder builder;
        String key = null;
        for(String decrypted : dictionary){
            builder = new StringBuilder(decrypt(decrypted,encryptedWord));
            int matches = counter(String.valueOf(builder), dictionary);
            if(matches > matchesMax){
                matchesMax = matches;
                decryptedWord = String.valueOf(builder);
                key = decrypted;
            }
        }


        return STR."key: \{key} Decrypted text: \{decryptedWord}";
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
}
