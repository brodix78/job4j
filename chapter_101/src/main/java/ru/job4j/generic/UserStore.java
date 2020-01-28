package ru.job4j.generic;

public class UserStore<User> extends AbstractStore {
    public UserStore(int size) {
        super(size);
    }
}
