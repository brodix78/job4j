package job4j.inout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(lines::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            line.replaceAll("\\s+", "");
            if (!line.isEmpty()
                    && (Character.isLetter(line.charAt(0)) || (Character.isDigit(line.charAt(0))))
                    && line.contains("=")
                    && line.indexOf("=") == line.lastIndexOf("=")
                    && !line.endsWith("=")) {
                int comment = line.contains("#") ? line.indexOf("#") : line.length();
                values.put(line.substring(0, line.indexOf("=")),
                        line.substring(line.indexOf("=") + 1, comment));
            }
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out =  new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
