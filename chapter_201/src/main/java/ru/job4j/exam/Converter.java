package ru.job4j.exam;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Converter {
    List<Map<String, String>> formatToMaps(String format);
    String asFormat(Object object);
}
