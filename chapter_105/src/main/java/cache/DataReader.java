package cache;

public interface DataReader<K, V> {

    V data(K key);

}
