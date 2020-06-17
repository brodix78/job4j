package ru.job4j.syncro;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final HashMap<Integer, User> storage= new HashMap<>();

    public synchronized boolean addUser(User user) {
        return user != null && storage.putIfAbsent(user.getId(), user) != null;
    }

    public synchronized boolean update(User user) {
        return user != null && storage.containsKey(user.getId())
                && storage.put(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return user != null && storage.remove(user.getId()) != null;
    }

    public synchronized boolean transfer(User sender, User receiver, int value) {
        boolean rsl = false;
        if (storage.containsKey(sender.getId()) && storage.containsKey(receiver.getId())
                && sender.getAmount() >= value) {
            sender.setAmount(sender.getAmount() - value);
            receiver.setAmount(receiver.getAmount() + value);
            rsl = true;
        }
        return rsl;
    }
}
