package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;

import BruteForce.BruteForce;
import CyphersOptions.Decryptor;
import CyphersOptions.Encryptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IntegrationTest {
    private final String textUS = """
            I love to hear her speak, yet well I know
            That music hath a far more pleasing sound*
            I grant I never saw a goddess go
            My mistress when she walks treads on the ground""";
    private final String textUA = """
            Любіть Україну, як сонце любіть,
            як вітер, і трави, і води...
            В годину щасливу і в радості мить,
            любіть у годину негоди.
            
            Любіть Україну у сні й наяву,
            вишневу свою Україну,
            красу її, вічно живу і нову,
            і мову її солов'їну.
            """;

    private Path inputFilePath_UA;
    private Path inputFilePath_EN;

    @TempDir
    private Path tempDir;

    @BeforeEach
    public void setUp() throws IOException {
        inputFilePath_UA = createTestFile("EN_Text.txt", textUA);
        inputFilePath_EN = createTestFile("US_Text.txt", textUA);
    }

    private Path createTestFile(String fileName, String content) throws IOException {
        Path filePath = tempDir.resolve(fileName);
        Files.writeString(filePath, content);
        return filePath;
    }

}
