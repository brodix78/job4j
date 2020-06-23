package ru.job4j.nba;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class BaseCache {

    private final ConcurrentHashMap<Integer,Base> cache = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        Base copy = Base.of(model);
        return copy != null && cache.putIfAbsent(copy.getId(), copy) == null;
    }

    public boolean update(Base model) throws OptimisticException{
        Base copy = Base.of(model);
        return model != null
                && cache.computeIfPresent(copy.getId(), (key, val) -> {
            if (val.getVersion() == copy.getVersion()) {
                return copy.updateVersion();
            } else {
                throw new OptimisticException("Versions collision");
            }
        }) != null;
    }

    public boolean delete(Base model) {
        Base copy = Base.of(model);
        return copy != null && cache.remove(copy.getId()) != null;
    }
}
