package ru.job4j.food;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class ControllQuality {

    private HashMap<Storage, Predicate<Food>> storages;

    public ControllQuality(List<Storage> storage) {
        storages = new HashMap<>();
        for (Storage st : storage) {
            storages.put(st, st.storeCondition());
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

    public HashMap<Food, Double> resort() {
        Storage temporary = new Unsorted();
        for (Storage storage : storages.keySet()) {
            Predicate<Food> expired = food -> !storages.get(storage).test(food);
            HashMap<Food, Double> expiredInStorage = storage.foodInStorage(expired);
            for (Food food : expiredInStorage.keySet()) {
                storage.moveFood(food, expiredInStorage.get(food), temporary);
            }
        }
        addToStorage(temporary.foodInStorage());
        return temporary.foodInStorage();
    }
}
