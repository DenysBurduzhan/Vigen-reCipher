package CyphersOptions;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@Setter
@Getter
public class Encryptor  {
    public String key;
    public String word;
    public static SettingsManagerImpl settingsManager = new SettingsManagerImpl();
    Encryptor(String key, String word) {
        this.key = key;
        this.word = word;
    }

    public static String encrypt(String key, String word) {
        char[] newKey = settingsManager.keyToWordLength(key, word);

        StringBuilder builder = new StringBuilder();
        ArrayList<Character> language = settingsManager.getLanguage(word);

        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            ArrayList<Character> upperOrLower = settingsManager.registerCheck(language,current);
            char keyChar = newKey[i];
            if (!Character.isUpperCase(current)) {
                keyChar = Character.toLowerCase(keyChar);
            }
            int keyIndex = settingsManager.findIndex(upperOrLower, keyChar);
            int cipherIndex = settingsManager.findIndex(upperOrLower, word.charAt(i));
            if (keyIndex < 0 || cipherIndex < 0) {
                builder.append(current);
                continue;
            }
            int indexSum = (keyIndex + cipherIndex) % upperOrLower.size();
            builder.append(upperOrLower.get(indexSum));

        }

        return builder.toString();
    }
}
