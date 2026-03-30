package CyphersOptions;


import lombok.Getter;
import lombok.Setter;

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
        return settingsManager.process(key, encryptedWord, false);
    }
}
