package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;

import BruteForce.BruteForce;
import Cipher.Cipher;
import CyphersOptions.Decryptor;
import CyphersOptions.Encryptor;
import org.junit.jupiter.api.Assumptions;
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
import java.util.List;
import java.util.stream.Stream;

public class IntegrationTest {
    private static final String ENCRYPT_COMMAND = "-e";
    private static final String DECRYPT_COMMAND = "-d";
    private static final String BF_COMMAND = "-b";
    private final String textEN = """
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
        inputFilePath_UA = createTestFile("EN_Text.txt", textEN);
        inputFilePath_EN = createTestFile("US_Text.txt", textUA);
    }

    private Path createTestFile(String fileName, String content) throws IOException {
        Path filePath = tempDir.resolve(fileName);
        Files.writeString(filePath, content);
        return filePath;
    }

    private Path execute(String command, Path inputFilePath, String key) throws IOException {
        List<Path> filesBefore = listFiles(tempDir);
        List<String> params = List.of(command, "-k", String.valueOf(key), "-f", inputFilePath.toString());
        try {
            Cipher.main(params.toArray(new String[0]));
        } catch (Exception e) {
            throw new RuntimeException("Execution failed", e);
        }
        return findNewFile(filesBefore);
    }

    private List<Path> listFiles(Path directory) throws IOException {
        try (Stream<Path> stream = Files.list(directory)) {
            return stream.toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to list files in directory: " + directory, e);
        }
    }

    private Path findNewFile(List<Path> filesBefore) throws IOException {
        List<Path> filesAfter = listFiles(tempDir);
        return filesAfter.stream()
                .filter(file -> !filesBefore.contains(file))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No new file was created"));
    }

    private String readFile(Path filePath) {
        Assumptions.assumeTrue(Files.exists(filePath), "File does not exist: " + filePath);
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            fail("Failed to read file: " + filePath, e);
            return null;
        }
    }
}

