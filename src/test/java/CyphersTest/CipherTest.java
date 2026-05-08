package CyphersTest;

import static org.junit.jupiter.api.Assertions.*;

import BruteForce.BruteForce;
import CyphersOptions.Decryptor;
import CyphersOptions.Encryptor;
import org.junit.jupiter.api.Test;


public class CipherTest {
    String text = "I love to hear her speak, yet well I know";
    String key = "good";
    String cipher = "O zcyk hc kkof kkf gskoy, esh zkzz L qbcz";
    @Test
    public void testBruteforce() {
        assertEquals("key: " + key + " text: " + text, BruteForce.bruteforce(cipher));
    }

    @Test
    public void testDecrypt() {
        assertEquals(text, Decryptor.decrypt(key, cipher));
    }

    @Test
    public void testEncrypt() {
        assertEquals(cipher, Encryptor.encrypt(key, text));
    }

    @Test
    public void testBackwardCompatibility(){
        assertEquals(text, Decryptor.decrypt(key,Encryptor.encrypt(key,text)));
    }

    @Test
    public void testRegister(){
        assertEquals(cipher, Encryptor.encrypt(key.toUpperCase(), text));
        assertEquals(cipher, Encryptor.encrypt(key.toLowerCase(), text));
    }
}
