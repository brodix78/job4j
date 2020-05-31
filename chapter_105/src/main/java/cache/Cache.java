package cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public abstract class Cache<K, V> {

    protected HashMap<K, SoftReference<V>> cacheMap = new HashMap<>();

    // Это написано только для теста!!!
    public SoftReference<V> getMapForTest(K key) {
        return cacheMap.get(key);
    }

    public V get(K key) {
        if (!cacheMap.containsKey(key) || cacheMap.get(key).get() == null) {
            V value = getData(key);
            if (value != null) {
                cacheMap.put(key, new SoftReference<>(value));
            }
        }
        return cacheMap.containsKey(key) ? cacheMap.get(key).get() : null;
    }

    protected V getData(K key) {
        return null;
    }
}