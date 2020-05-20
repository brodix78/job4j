package gc;

import java.util.ArrayList;
import InstrumentationAgent;

public class UserGCCall {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Here I am");
    }

    private void memories() {
        Runtime runtime = Runtime.getRuntime();
        double mem = runtime.freeMemory();
        ArrayList<UserGCCall> users = new ArrayList<>();
        System.out.println("Free memory:" + mem);
        for (int i = 0; i < 10e3; i++) {
            users.add(new UserGCCall());
        }
        mem = (mem - runtime.freeMemory()) / users.size();
        int usersQuantity = (int) (runtime.freeMemory() / mem);
        for (int i = 0; i < usersQuantity * 1.6; i++) {
            users.add(new UserGCCall());
        }
        for (int i = 0; i < 10e2; i++) {
            users.add(new UserGCCall());
            users.set(i, null);
            System.out.print("");
        }
        for (int i = users.size() - 1; i > -1; i--) {
            UserGCCall user = users.get(i);
            users.remove(i);
        }
    }

    public static void printObjectSize(Object object) {
        System.out.println("Object type: " + object.getClass() +
                ", size: " + InstrumentationAgent.getObjectSize(object) + " bytes");
    }

    public static void main(String[] args) {
        UserGCCall userGCCall = new UserGCCall();
        userGCCall.memories();
    }
}
