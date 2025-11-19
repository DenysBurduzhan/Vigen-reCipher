package Cipher;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {
    private static ArrayList<Character> eng = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

    private static ArrayList<Character> ukr = new ArrayList<>(Arrays.asList(
            'а', 'б', 'в', 'г', 'ґ', 'д', 'е', 'є', 'ж', 'з', 'и', 'і', 'ї', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я'));

    public static ArrayList<Character> getUkr() {
        return ukr;
    }

    public static void setUkr(ArrayList<Character> ukr) {
        Constants.ukr = ukr;
    }

    public static ArrayList<Character> getEng() {
        return eng;
    }

    public static void setEng(ArrayList<Character> eng) {
        Constants.eng = eng;
    }
}
