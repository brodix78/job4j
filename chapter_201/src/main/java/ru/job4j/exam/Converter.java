package ru.job4j.exam;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Converter {

    /**
     *
     * @param format as a String
     * @return format as List
     */
    List<Map<String, String>> formatToMaps(String format);

    /**
     *
     * @param object for convert in special format view
     * @return object converted to format
     */
    String asFormat(Object object);
}
