package CyphersOptions;

import lombok.Getter;
import lombok.Setter;
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
        return settingsManager.process(key, word, true);
    }
}
