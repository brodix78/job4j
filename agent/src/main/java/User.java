import java.util.ArrayList;
import java.util.HashMap;

public class User {

    private String name;

    private HashMap<Integer, Double> hardContent;
    private String sas;

    public User(String name) {
        this.name = name;
    }

    public User() {};

    public String getName() {
        return name;
    }

    public void init() {
        hardContent = new HashMap<>();
        for (double i = 1; i < 3000; i++) {
            hardContent.put((int) i, i);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Here I am");
    }

    private void memories() throws Throwable {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Total memory, m: " +runtime.totalMemory() / 1024 / 1024);
        User a = new User("a");
        System.out.println("Free memory, k: " +runtime.freeMemory() / 1024);
        a = null;
        long mem = runtime.freeMemory();
        double totalLimit = runtime.totalMemory() * 0.16;
        User[] users = new User[(int ) (mem / 64 / 10)];
        for (int i = 0; i < users.length; i++) {
            users[i] = new User();
            if (runtime.freeMemory() < totalLimit) {
                break;
            }
        }
    }

    public static void main(String[] args) throws Throwable {
        User user = new User("Petya");
        user.memories();
    }

}
