package ru.job4j.food;

import java.util.Date;

public class Meat extends Food {

    private String meatType;

    public Meat(String name, String meatType, Date createDate, Date expiredDate, double price) {
        super(name, createDate, expiredDate, price);
        this.meatType = meatType;
    }

    public String getMeatType() {
        return meatType;
    }
}