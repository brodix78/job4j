package cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class CacheTwo<K, V> {

    private final DataReader<K, V> read;
    private HashMap<K, SoftReference<V>> cache = new HashMap<>();

    public CacheTwo (DataReader<K, V> dataReader) {
        this.read = dataReader;
    }

    public V get(K key) {
        if (!cache.containsKey(key) || cache.get(key).get() == null) {
            V value = read.data(key);
            if (value != null) {
                cache.put(key, new SoftReference<>(value));
            }
        }
        return cache.getOrDefault(key, new SoftReference<>(null)).get();
    }

}
