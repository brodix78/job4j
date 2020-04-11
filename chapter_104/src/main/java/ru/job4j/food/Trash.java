package ru.job4j.food;

import java.util.Date;
import java.util.function.Predicate;

public class Trash extends Storage{

    @Override
    public Predicate<Food> getPredicate() {
        return food -> {
            Date now = new Date();
            float rsl = (float) (now.getTime() - food.getCreateDate().getTime()) /
                    (food.getExpiredDate().getTime() - food.getCreateDate().getTime());
            return rsl >= 1;
        };
    }
}
