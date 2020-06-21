package ru.job4j.nba;

import java.util.concurrent.ConcurrentHashMap;

public class BaseCache {

    private ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    public boolean add(Base base) {
        return base != null && cache.putIfAbsent(base.getId(), base) != null;
    }

    public boolean update(Base base) throws OptimisticException{
        return base != null
                    && cache.computeIfPresent(base.getId(), (key, val) -> val.updateVersion(base)) != null
                    && cache.put(base.getId(), base) != null;
    }

    public boolean delete(Base base) {
        return base != null && cache.remove(base.getId()) != null;
    }
}
