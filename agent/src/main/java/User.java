import java.util.ArrayList;
import java.util.HashMap;

public class User {

    private String name;
    private Double valueOne;
    private Double valueTwo;
    private HashMap<Integer, Double> hardContent;
    private String sas;

    public User(String name) {
        this.name = name;
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

    public static void main(String[] args) throws Throwable {
        User user = new User("Petya");
        user.memories();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Here I am");
    }

    private void memories() throws Throwable {
        Runtime runtime = Runtime.getRuntime();
        long mem = runtime.freeMemory();
        long memT = runtime.totalMemory();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory before users " + usedMemory);
        System.out.println(runtime.totalMemory());
        ArrayList<User> users = new ArrayList<>();
        long usedMemory2 = runtime.totalMemory() - runtime.freeMemory() - usedMemory;
        User a = new User("a");
        a.init();
        User b = new User("a");
        a.finalize();
        User c = new User("c");

        mem = runtime.freeMemory();
   /*     int quantaty = 100000;
        for (int i = 0; i < 1; i++) {
            users.add(new User("Ivan"));
        }

        System.out.println(mem - runtime.freeMemory());
        for (int i = quantaty - 1; i >= 0; i--) {
            users.remove(i);
        }*/

        System.out.println("Free memory:" + mem);
        System.out.println(memT - runtime.totalMemory());
        mem = runtime.freeMemory();
        memT = runtime.totalMemory();
        mem = runtime.freeMemory() + memT;
        memT = mem + 10;

    }
}
