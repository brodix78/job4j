package ru.job4j.exam;

import java.util.List;
import java.util.Map;

public interface Child {

    List<String> links();
    Child getInstance(Map<String, String> fields);
}
