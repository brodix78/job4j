package cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TxtReader implements DataReader<String, String> {

    private Path folder;

    public TxtReader(String path) {
        this.folder = Paths.get(path);
    }

    @Override
    public String readData(String key) {
        String rsl = null;
        Path file = Paths.get(String.format("%s/%s.txt", this.folder, key));
        if (Files.exists(file)) {
            try {
                rsl = new String(Files.readAllBytes(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rsl;
    }
}
