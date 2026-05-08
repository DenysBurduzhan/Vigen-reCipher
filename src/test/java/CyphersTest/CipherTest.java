package CyphersTest;

import static org.junit.jupiter.api.Assertions.*;

import BruteForce.BruteForce;
import CyphersOptions.Decryptor;
import CyphersOptions.Encryptor;
import org.junit.jupiter.api.Test;


public class CipherTest {
    String text = "I love to hear her speak, yet well I know";
    String shorterText = "HI";
    String key = "good";
    String cipher = "O zcyk hc kkof kkf gskoy, esh zkzz L qbcz";
    @Test
    public void testBruteforce() {
        assertEquals("key: " + key + " text: " + text, BruteForce.bruteforce(cipher));
    }

    @Test
    public void shouldDecryptCipherUsingKey() {
        assertEquals(text, Decryptor.decrypt(key, cipher));
    }

    @Test
    public void shouldEncryptTextUsingKey() {
        assertEquals(cipher, Encryptor.encrypt(key, text));
    }

    @Test
    public void testEncryptDecryptSymmetry(){
        assertEquals(text, Decryptor.decrypt(key,Encryptor.encrypt(key,text)));
    }

    @Test
    public void shouldIgnoreCaseOfKey(){
        assertEquals(cipher, Encryptor.encrypt(key.toUpperCase(), text));
        assertEquals(cipher, Encryptor.encrypt(key.toLowerCase(), text));
    }

    @Test
    public void shouldPreserveTextCase(){
        assertEquals(cipher.toUpperCase(), Encryptor.encrypt(key, text.toUpperCase()));
        assertEquals(cipher.toLowerCase(), Encryptor.encrypt(key, text.toLowerCase()));
    }

    @Test
    public void testEmptyText(){
        assertThrows(IllegalArgumentException.class, ()-> Encryptor.encrypt(key, ""));
    }

    @Test
    public void testTextShorterThanKey(){
        assertEquals("NW", Encryptor.encrypt(key, shorterText));
    }

    @Test
    public void testEmptyKey(){
        assertThrows(IllegalArgumentException.class, ()-> Encryptor.encrypt("", text));
    }
}
