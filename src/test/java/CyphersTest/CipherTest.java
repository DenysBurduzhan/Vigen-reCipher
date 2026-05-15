package CyphersTest;

import static org.junit.jupiter.api.Assertions.*;

import BruteForce.BruteForce;
import Constants.Constants;
import CyphersOptions.Decryptor;
import CyphersOptions.Encryptor;
import CyphersOptions.SettingsManagerImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;



public class CipherTest {
    private final String text = "I love to hear her speak, yet well I know";
    private final String key = "good";
    private final String encrypted = "O zcyk hc kkof kkf gskoy, esh zkzz L qbcz";
    private final SettingsManagerImpl settingsManager = new SettingsManagerImpl();

    private void assertEncrypted(String expectedCipher,String key, String text) {
        assertEquals(expectedCipher, Encryptor.encrypt(key, text));
    }

    private void assertDecrypted(String expectedText, String key, String cipherText) {
        assertEquals(expectedText, Decryptor.decrypt(key, cipherText));
    }

    private void assertEncryptionCycle(String text, String key) {
        String encrypted = Encryptor.encrypt(key, text);
        String decrypted = Decryptor.decrypt(key, encrypted);

        assertEquals(text, decrypted);
    }
    @Test
    public void testBruteforce() {
        assertEquals("key: " + key + " text: " + text, BruteForce.bruteforce(encrypted));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "move, onhwpofxq, abcabcabc"
    })
    public void testBruteforceCannotDecryptCipher(String key, String text, String cipher) {
        assertEquals("key: " + key + " text: " + text, BruteForce.bruteforce(cipher));
    }

    @ParameterizedTest
    @CsvSource({
            "RIJVS, KEY, HELLO",
            "NW, GOOD, HI",
    })
    public void testShouldDecryptCipherUsingKey(String cipher,String key, String text) {
        assertDecrypted(text, key, cipher);
    }

    @ParameterizedTest
    @CsvSource({
            "RIJVS, KEY, HELLO",
            "NW, GOOD, HI",
    })
    public void testShouldEncryptTextUsingKey(String cipher,String key, String text) {
        assertEncrypted(cipher, key, text);
    }

    @ParameterizedTest
    @CsvSource({
            "I LOVE TO HEAR , good",
            "i love to hear , GOOD"
    })
    public void testEncryptDecryptSymmetry(String text, String key) {
        assertEncryptionCycle(text, key);
    }


    @ParameterizedTest
    @CsvSource({
            "O ZCYK HC KKOF, good, I LOVE TO HEAR",
            "o zcyk hc kkof, GOOD, i love to hear"
    })
    void testShouldPreserveTextCase(String expected, String key, String input) {
        assertEquals(expected, Encryptor.encrypt(key, input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    public void testEmptyTextAndEmptyKey(String strings){
        assertThrows(IllegalArgumentException.class, ()-> Encryptor.encrypt(key, strings));
        assertThrows(IllegalArgumentException.class, ()-> Encryptor.encrypt(strings, text));
    }

    @ParameterizedTest
    @CsvSource({"NW, HI, good"})
    public void testTextShorterThanKey(String expected , String text, String key) {
        assertEquals(expected, Encryptor.encrypt(key, text));
    }

    @ParameterizedTest
    @CsvSource({
            "RIJVS111, KEY, HELLO111",
            "NW!!, GOOD, HI!!",
            "12345, KEY, 12345",
            "@#$%, GOOD, @#$%",
    })
    public void testSpecialChars(String cipher,String key, String text){
        assertEquals(cipher, Encryptor.encrypt(key, text));
        assertEquals(text, Decryptor.decrypt(key, cipher));
    }

    @Test
    @DisplayName("Languages test")
    void testLanguage() {
            assertEquals(Constants.getEng(), settingsManager.getLanguage("good"));
            assertEquals(Constants.getUkr(), settingsManager.getLanguage("калина"));
    }
}
