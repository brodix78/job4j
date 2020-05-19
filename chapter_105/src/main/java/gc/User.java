package gc;

import java.util.LinkedHashMap;

public class User {

    private LinkedHashMap<Double, Double> hardContent;

    public void init() {
        hardContent = new LinkedHashMap<>();
        for (double i = 0; i < 10e3; i++) {
            hardContent.put(i, i);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Here I am");
    }
}