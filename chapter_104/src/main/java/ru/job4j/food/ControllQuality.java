package ru.job4j.food;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class ControllQuality {

    private HashMap<Storage, Predicate<Food>> storages;

    public ControllQuality(List<Storage> storage) {
        storages = new HashMap<>();
        for (Storage st : storage) {
            storages.put(st, st.getPredicate());
        }
    }

    public ControllQuality(HashMap<Storage, Predicate<Food>> storage) {
        this.storages = storage;
    }

    public HashMap<Food, Double> addToStorage (HashMap<Food, Double> food) {
        for (Food f : food.keySet())
            for (Storage storage : storages.keySet()) {
                if (storages.get(storage).test(f)) {
                    storage.addFood(f, food.get(f));
                    food.remove(f);
                    break;
                }
            }
        return food;
    }
}
