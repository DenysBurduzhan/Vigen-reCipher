package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;

import BruteForce.BruteForce;
import Cipher.Cipher;
import CyphersOptions.Decryptor;
import CyphersOptions.Encryptor;
import org.junit.jupiter.api.*;
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
        inputFilePath_UA = createTestFile("UA_Text.txt", textUA);
        inputFilePath_EN = createTestFile("EN_Text.txt", textEN);
    }

    private Path createTestFile(String fileName, String content) throws IOException {
        Path filePath = tempDir.resolve(fileName);
        Files.writeString(filePath, content);
        return filePath;
    }

    private Path execute(String command,String key, Path inputFilePath) throws IOException {
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

    @Nested
    @DisplayName("File tests")
    class FileTests {

        @Nested
        @DisplayName("ENCRYPT")
        class EncryptFileTests {

            @Test
            @DisplayName("File should be created")
            void testFileCreation() throws IOException {
                Path encrypted = execute(ENCRYPT_COMMAND, "good", inputFilePath_EN);
                assertTrue(Files.exists(encrypted), "Encrypted file does not exist:");
            }

            @Test
            @DisplayName("File should have a marker '[ENCRYPTED]'")
            void TestEncryptFileMarker() throws IOException {
                Path encrypted = execute(ENCRYPT_COMMAND, "good", inputFilePath_EN);
                assertTrue(encrypted.getFileName().toString().contains("[ENCRYPTED]"), "Encrypted file doesn't have '[ENCRYPTED]' marker. File name: " + encrypted.getFileName());
            }
        }
        @Nested
        @DisplayName("DECRYPT")
        class DecryptFileTests {

            @Test
            @DisplayName("File should be created")
            void testFileCreation() throws IOException {
                Path encrypted = execute(ENCRYPT_COMMAND, "good", inputFilePath_EN);
                Path decrypted = execute(DECRYPT_COMMAND, "good", encrypted);
                assertTrue(Files.exists(decrypted), "Decrypted file does not exist:");
            }

            @Test
            @DisplayName("File should have a marker '[DECRYPTED]'")
            void TestEncryptFileMarker() throws IOException {
                Path encrypted = execute(ENCRYPT_COMMAND, "good", inputFilePath_EN);
                Path decrypted = execute(DECRYPT_COMMAND, "good", encrypted);
                assertTrue(decrypted.getFileName().toString().contains("[DECRYPTED]"), "Decrypted file doesn't have '[DECRYPTED]' marker. File name: " + decrypted.getFileName());
            }
        }
    }
    @Nested
    @DisplayName("Validation")
    class ValidationTests {
        @Test
        @DisplayName("File not exists exception should be handled")
        void fileNotExists() {
            Path fakeFilePath = Path.of("/fake/path/file.txt");

            String[] params = {ENCRYPT_COMMAND, "-k", "good" , "-f", fakeFilePath.toString()};

            assertThrows(IOException.class,
                    () -> Cipher.main(params));
        }
    }
}

