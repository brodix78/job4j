package ru.job4j.collection;

import java.util.Comparator;

public class UserSortByAge implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        return Integer.compare(user1.getAge(), user2.getAge());
    }
}
