package lambda;
import java.util.stream.Stream;
public class Holder {
    public String key, value;
    public Holder(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
}