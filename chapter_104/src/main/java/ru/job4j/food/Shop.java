package ru.job4j.food;

import java.util.Date;
import java.util.function.Predicate;

public class Shop extends Storage {

    @Override
    public Predicate<Food> storeCondition() {
        return food -> {
            Date now = new Date();
            float rsl = (float) (now.getTime() - food.getCreateDate().getTime()) /
                    (food.getExpiredDate().getTime() - food.getCreateDate().getTime());
            if (rsl >= 0.75) {
                food.setDiscount(true);
            }
            return rsl >= 0.25 &&rsl < 1;
        };
    }
}
