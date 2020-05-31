package cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCache extends Cache<String, String> {

    private Path folder;

    public FileCache(String path) {
        this.folder = Paths.get(path);
    }

    @Override
    protected String getData(String key) {
        String rsl = null;
        if (key.endsWith(".txt")) {
            Path file = Paths.get(folder + "/" + key);
            if (Files.exists(file)) {
                try {
                    rsl = new String(Files.readAllBytes(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rsl;
    }
}