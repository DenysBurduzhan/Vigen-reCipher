package CyphersTest;

import static org.junit.jupiter.api.Assertions.*;

import BruteForce.BruteForce;
import CyphersOptions.Decryptor;
import CyphersOptions.Encryptor;
import org.junit.jupiter.api.Test;


public class CipherTest {
    @Test
    public void testBruteforce() {
        assertEquals("key: good text: I love to hear her speak, yet well I know", BruteForce.bruteforce("O zcyk hc kkof kkf gskoy, esh zkzz L qbcz"));
    }

    @Test
    public void testDecrypt() {
        assertEquals("I love to hear her speak, yet well I know", Decryptor.decrypt("good", "O zcyk hc kkof kkf gskoy, esh zkzz L qbcz"));
    }

    @Test
    public void testEncrypt() {
        assertEquals("O zcyk hc kkof kkf gskoy, esh zkzz L qbcz", Encryptor.encrypt("good", "I love to hear her speak, yet well I know"));
    }
}
