package ru.job4j.exam;

import java.util.List;
import java.util.Map;

public interface Factory<T> {
    /**
     *
     * @return Names of required links for product creation
     */
    List<String> links();

    /**
     *
     * @param fields as a key with values (in String) needed for product initialization
     * @return product
     */
    T getInstance(Map<String, String> fields);
    T getCopy(T original);
}
