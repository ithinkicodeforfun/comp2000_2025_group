import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StageReader {
    public static Stage readStage(String path) throws IOException {
        Stage stage = new Stage();
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.err.println("Data file not found: " + path);
            throw e;
        }

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split("=");
            if (parts.length != 2 || parts[1].trim().isEmpty()) {
                System.err.println("Empty actor: " + line);
                continue;
            }
            String loc = parts[0].trim();
            String actorType = parts[1].trim();

            if (loc.length() < 2) {
                System.err.println("Invalid location: " + loc);
                continue;
            }
            if (col < 'A' || col > 'T' || row < 1 || row > 20) {
                System.err.println("Location outside grid: " + loc);

            }
        }
        return stage;
    }
    //test
    //test2
    
    
}