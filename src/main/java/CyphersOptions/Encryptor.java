package CyphersOptions;


public class Encryptor  {
    public static SettingsManagerImpl settingsManager = new SettingsManagerImpl();

    public static String encrypt(String key, String word) {
        return settingsManager.process(key, word, true);
    }
}
