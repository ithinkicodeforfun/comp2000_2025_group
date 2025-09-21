import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StageReader {
    public static Stage readStage(String path) throws IOException {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.err.println("Data file not found: " + path);
            throw e;
        }

        for (String line : lines) {
          if (line.trim().isEmpty()) {
          System.err.println("Empty line found in stage file.");
        }

            }
            if (col < 'A' || col > 'T' || row < 1 || row > 20) {
                System.err.println("Location outside grid: " + loc);

            }
        }
    }
}