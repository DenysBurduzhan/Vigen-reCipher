package CyphersOptions;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
public class Decryptor {
    public String key;
    public String encryptedWord;
    public static SettingsManagerImpl settingsManager = new SettingsManagerImpl();

    public Decryptor(String key, String encryptedWord) {
        this.key = key;
        this.encryptedWord = encryptedWord;
    }

    public static String decrypt(String key, String encryptedWord) {
        char[] newKey = settingsManager.keyToWordLength(key, encryptedWord);
        StringBuilder builder = new StringBuilder();

        ArrayList<Character> language = settingsManager.getLanguage(encryptedWord);

        for (int i = 0; i < encryptedWord.length(); i++) {
            char current = encryptedWord.charAt(i);
            ArrayList<Character> upperOrLower = settingsManager.registerCheck(language,current);
            char keyChar = newKey[i];
            if (!Character.isUpperCase(current)) {
                keyChar = Character.toLowerCase(keyChar);
            }
            int keyIndex = settingsManager.findIndex(upperOrLower, keyChar);
            int cipherIndex = settingsManager.findIndex(upperOrLower, current);
            if (keyIndex < 0 || cipherIndex < 0) {
                builder.append(current);
                continue;
            }
            int plainIndex = (cipherIndex - keyIndex + upperOrLower.size()) % upperOrLower.size();
            builder.append(upperOrLower.get(plainIndex));
        }

        return builder.toString();
    }
}
