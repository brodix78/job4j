package ru.job4j.exam;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Converter {
    List<Map<String, String>> formatToMap(String format);
    String mapToFormat(Map<String, String> map) throws JsonProcessingException;
    String ListToFormat(List<Map<String, String>> list) throws JsonProcessingException;
}
