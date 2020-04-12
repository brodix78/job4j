package ru.job4j.food;

import java.util.Date;
import java.util.function.Predicate;

public class Warehouse extends Storage {

    @Override
    public Predicate<Food> storeCondition() {
        return food -> {
            Date now = new Date();
            float rsl = (float) (now.getTime() - food.getCreateDate().getTime()) /
                    (food.getExpiredDate().getTime() - food.getCreateDate().getTime());
            return rsl >= 0 && rsl < 0.25;
        };
    }
}
