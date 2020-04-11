package ru.job4j.food;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Food {
    private String name;
    private Date createDate;
    private Date expiredDate;
    private double price;
    private boolean discount;

    public Food(String name, Date createDate, Date expiredDate, double price) {
        this.name = name;
        this.createDate = createDate;
        this.expiredDate = expiredDate;
        this.price = price;
        this.discount = false;
    }

    public String getName() {
        return name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "Food{" +
                "name='" + name + '\'' +
                ", createDate=" + dateFormat.format(createDate) +
                ", expiredDate=" + dateFormat.format(expiredDate) +
                ", price=" + price +
                ", have discount: " + discount + "%" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0 &&
                Objects.equals(name, food.name) &&
                Objects.equals(createDate, food.createDate) &&
                Objects.equals(expiredDate, food.expiredDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, createDate, expiredDate, price);
    }
}
