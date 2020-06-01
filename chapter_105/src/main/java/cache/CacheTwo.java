package cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class CacheTwo<K, V> {

    private final DataReader<K, V> dataReader;
    protected HashMap<K, SoftReference<V>> cacheMap = new HashMap<>();

    public CacheTwo (DataReader<K, V> dataReader) {
        this.dataReader = dataReader;
    }

    public V get(K key) {
        if (!cacheMap.containsKey(key) || cacheMap.get(key).get() == null) {
            V value = dataReader.readData(key);
            if (value != null) {
                cacheMap.put(key, new SoftReference<>(value));
            }
        }
        return cacheMap.containsKey(key) ? cacheMap.get(key).get() : null;
    }

    // Этот метод написан только для теста!!!
    public SoftReference<V> getMapForTest(K key) {
        return cacheMap.get(key);
    }
}
