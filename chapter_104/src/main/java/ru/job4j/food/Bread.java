package ru.job4j.food;

import java.util.Date;

public class Bread extends Food {

    private String breadType;

    public Bread(String name, String breadType, Date createDate, Date expiredDate, double price) {
        super(name, createDate, expiredDate, price);
        this.breadType = breadType;
    }

    public String getBreadType() {
        return breadType;
    }
}