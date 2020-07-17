package ru.job4j.exam;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Converter<T> {
    List<Map<String, String>> formatToMap(String format);
    String toFormat(List<T> items) throws JsonProcessingException;
}
