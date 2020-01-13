package ru.job4j.collection;

import java.util.Comparator;

public class UserSortByAgeRev implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        return Integer.compare(user2.getAge(), user1.getAge());
    }
}
