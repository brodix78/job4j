package cache;

public interface DataReader<K, V> {

    V readData(K key);

}
