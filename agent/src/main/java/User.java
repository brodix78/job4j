import java.util.HashMap;

public class User {

    private String name;
    private Double valueOne;
    private Double valueTwo;
    private HashMap<Integer, Double> hardContent;
    private String sas;

    public User(String name, Double valueOne, Double valueTwo, String sas) {
        this.name = name;
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
        this.sas = sas;
    }

    public String getSas() {
        return sas;
    }

    public String getName() {
        return name;
    }

    public Double getValueOne() {
        return valueOne;
    }

    public void init() {
        hardContent = new HashMap<>();
        for (double i = 1; i < 1000; i++) {
            hardContent.put((int) i, i);
        }
    }

    public Double sum() {
        Double rsl = 0.0;
        for (Integer key:hardContent.keySet()) {
            rsl += hardContent.get(key);
        }
        return rsl;
    }
}
