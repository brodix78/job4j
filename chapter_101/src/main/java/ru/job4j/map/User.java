package ru.job4j.map;

import java.util.Calendar;

public class User {

    public User(String name, Calendar birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    private String name;
    private int children;
    private Calendar birthDate;

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }
}
