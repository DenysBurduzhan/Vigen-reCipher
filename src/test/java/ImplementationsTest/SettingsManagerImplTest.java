package ImplementationsTest;
import static org.junit.jupiter.api.Assertions.*;

import Constants.Constants;
import CyphersOptions.SettingsManagerImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SettingsManagerImplTest {
    SettingsManagerImpl settingsManagerImpl = new SettingsManagerImpl();

    @Test
    public void testFindIndex() {
        assertEquals(1, settingsManagerImpl.findIndex(Constants.getEng(), 'B'));
    }

    @Test
    public void testKeyToWordLength() {
        char[] c = settingsManagerImpl.keyToWordLength("a", "abc");
        assertEquals(3, c.length);
    }

    @Test
    public void testGetLanguage() {
        assertEquals(Constants.getEng(), settingsManagerImpl.getLanguage("eng"));
    }
}
