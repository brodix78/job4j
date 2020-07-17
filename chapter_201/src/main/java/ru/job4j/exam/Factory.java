package ru.job4j.exam;

import java.util.List;
import java.util.Map;

public interface Factory<T> {
    List<String> links();
    T getInstance(Map<String, String> fields);
    T getCopy(T original);
}
