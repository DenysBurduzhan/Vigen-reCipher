package CyphersOptions;



public class Decryptor {
    public static SettingsManagerImpl settingsManager = new SettingsManagerImpl();

    public static String decrypt(String key, String encryptedWord) {
        return settingsManager.process(key, encryptedWord, false);
    }
}
