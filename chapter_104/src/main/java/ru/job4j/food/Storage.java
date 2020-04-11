package ru.job4j.food;

import java.util.HashMap;
import java.util.function.Predicate;

public class Storage{

    protected HashMap<Food, Double> storage = new HashMap<>();

    public HashMap<Food, Double> foodInStorage(Predicate<Food> foodPredicate) {
        HashMap<Food, Double> food = new HashMap<>();
        storage.keySet().stream().filter(foodPredicate).forEach(f -> food.put(f, storage.get(f)));
        return food;
    }


    public HashMap<Food, Double> foodInStorage () {
        return storage;
    }

    public void addFood (Food foodPos, Double foodQuan) {
        if (storage.containsKey(foodPos)) {
            storage.put(foodPos, storage.get(foodPos) + foodQuan);
        } else {
            storage.put(foodPos, foodQuan);
        }
    }

    public boolean moveFood (Food foodPos, Double foodQuan, Storage toStorage) {
        boolean rsl = false;
        if (storage.containsKey(foodPos) && storage.get(foodPos) >= foodQuan) {
            if (storage.get(foodPos) > foodQuan) {
                storage.put(foodPos, storage.get(foodPos) - foodQuan);
            } else {
                storage.remove(foodPos);
            }
            toStorage.addFood(foodPos, foodQuan);
            rsl = true;
        }
        return rsl;
    }

    public Predicate<Food> getPredicate() {
        return food -> false;
    }
}