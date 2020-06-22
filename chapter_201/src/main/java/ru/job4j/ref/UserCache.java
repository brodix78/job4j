package ru.job4j.ref;


import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        User copy = User.of(user.getName(), id.incrementAndGet());
        users.put(copy.getId(), copy);
    }

    public User findById(int id) {
        return User.of(users.get(id).getName(), id);
    }

    public List<User> findAll() {
        ArrayList<User> copy = new ArrayList<>();
        users.values().forEach(user -> copy.add(User.of(user.getName(), user.getId())));
        return copy;
    }
}
