package CyphersTest;

import static org.junit.jupiter.api.Assertions.*;

import BruteForce.BruteForce;
import Constants.Constants;
import CyphersOptions.Decryptor;
import CyphersOptions.Encryptor;
import CyphersOptions.SettingsManagerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;



public class CipherTest {
    String text = "I love to hear her speak, yet well I know";
    String shorterText = "HI";
    String key = "good";
    String cipher = "O zcyk hc kkof kkf gskoy, esh zkzz L qbcz";
    private final SettingsManagerImpl settingsManager = new SettingsManagerImpl();
    @Test
    public void testBruteforce() {
        assertEquals("key: " + key + " text: " + text, BruteForce.bruteforce(cipher));
    }

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
        assertEquals(text, Decryptor.decrypt(key, cipher));
    }

    @ParameterizedTest
    @CsvSource({
            "RIJVS, KEY, HELLO",
            "NW, GOOD, HI",
    })
    public void testShouldEncryptTextUsingKey(String cipher,String key, String text) {
        assertEquals(cipher, Encryptor.encrypt(key, text));
    }

    @Test
    public void testEncryptDecryptSymmetry(){
        assertEquals(text, Decryptor.decrypt(key,Encryptor.encrypt(key,text)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"good", "GOOD"})
    public void testShouldIgnoreCaseOfKey(String strings){
        assertEquals(cipher, Encryptor.encrypt(strings, text));
    }

    @ParameterizedTest
    @CsvSource({
            "O ZCYK HC KKOF, I LOVE TO HEAR",
            "o zcyk hc kkof, i love to hear"
    })
    void testShouldPreserveTextCase(String expected, String input) {
        assertEquals(expected, Encryptor.encrypt(key, input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    public void testEmptyTextAndEmptyKey(String strings){
        assertThrows(IllegalArgumentException.class, ()-> Encryptor.encrypt(key, strings));
        assertThrows(IllegalArgumentException.class, ()-> Encryptor.encrypt(strings, text));
    }

    @Test
    public void testTextShorterThanKey(){
        assertEquals("NW", Encryptor.encrypt(key, shorterText));
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
