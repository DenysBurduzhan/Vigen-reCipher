package ImplementationsTest;
import static org.junit.jupiter.api.Assertions.*;

import BruteForce.BruteForceImpl;
import Dictionary.Dictionary;
import org.junit.jupiter.api.Test;

public class BruteForceImplTest {
    BruteForceImpl bruteForceImpl = new BruteForceImpl();
    @Test
    public void testGetLanguage(){
        assertEquals(Dictionary.getDictionary(),bruteForceImpl.getLanguage("English"));
    }

    @Test
    public void testFindKey(){
        assertEquals("good", bruteForceImpl.findKey("key: good text: I love to hear her speak, yet well I know"));
    }
}
