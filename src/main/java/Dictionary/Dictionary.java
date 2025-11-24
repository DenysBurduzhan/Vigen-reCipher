package Dictionary;

import lombok.Getter;

import java.util.Arrays;

public class Dictionary {
    @Getter
    private static  String[] dictionary = new String[]{"take", "or", "for", "lemon" , "hello", "of", "as", "his", "that", "he", "was", "for", "on", "are", "with", "they", "be", "at", "by", "word", "have", "play", "my", "world", "there", "those", "victory", "is", "not", "to", "in", "the"};

    @Getter
    private static  String[] dictionaryUKR = new String[]{"Ми", "всі", "щось", "Привіт", "хто", "що", "коли", "слово", "ти", "він", "вона", "воно", "вони", "йому", "робить", "працює", "каже", "розмовляє", "співає", "чує", "що робить", "збирає", "поле", "був", "була", "ні", "так", "тому що"};


    public static void setDictionary(String[] dictionary) {
        Dictionary.dictionary = dictionary;
    }

    public static void setDictionaryUKR(String[] dictionaryUKR) {
        Dictionary.dictionaryUKR = dictionaryUKR;
    }
}
