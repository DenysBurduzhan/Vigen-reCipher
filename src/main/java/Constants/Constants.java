package Constants;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {
    private static ArrayList<Character> eng = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

    private static ArrayList<Character> ENG_UPPER = new ArrayList<>(Arrays.asList(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
    private static ArrayList<Character> ukr = new ArrayList<>(Arrays.asList(
            'а', 'б', 'в', 'г', 'ґ', 'д', 'е', 'є', 'ж', 'з', 'и', 'і', 'ї', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я'));

    private static ArrayList<Character> UKR_UPPER = new ArrayList<>(Arrays.asList(
            'А', 'Б', 'В', 'Г', 'Ґ', 'Д', 'Е', 'Є', 'Ж', 'З', 'И', 'І', 'Ї', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ю', 'Я'));
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

    public static ArrayList<Character> getEngUpper() {
        return ENG_UPPER;
    }

    public static void setEngUpper(ArrayList<Character> engUpper) {
        ENG_UPPER = engUpper;
    }

    public static ArrayList<Character> getUkrUpper() {
        return UKR_UPPER;
    }

    public static void setUkrUpper(ArrayList<Character> ukrUpper) {
        UKR_UPPER = ukrUpper;
    }
}
