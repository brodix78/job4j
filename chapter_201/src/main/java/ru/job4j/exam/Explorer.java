package ru.job4j.exam;

import java.util.List;
import java.util.Map;

public interface Explorer {
    List<Map<String, String>> call();
    Explorer getInstance(String source);
}
