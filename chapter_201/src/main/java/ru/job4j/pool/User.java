package ru.job4j.pool;

public class User {
    private final String email;
    private final String name;

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public static User of(User user) {
        return user != null ? new User(user.getEmail(), user.getName()) : null;
    }
}
