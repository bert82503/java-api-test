package sun.nio.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

/**
 * Test of {@link Paths}, {@link Files}.
 *
 * @since 2020-03-01
 */
public class PathTest {

    @Test
    public void createFile() throws IOException {
        String dir = System.getProperty("user.home") + "/temp";
        Path path = Paths.get(dir);
        if (Files.notExists(path)) {
            Files.createDirectories(path);
        }

        Path file = Paths.get(dir + "/test.txt");
        if (Files.notExists(file)) {
            Files.createFile(file);
        }
    }
}
