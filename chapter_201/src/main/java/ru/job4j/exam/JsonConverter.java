package ru.job4j.exam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonConverter implements Converter{

    public List<Map<String, String>> formatToMap(String json) {
        List<Map<String, String>> call;
        try {
            ObjectMapper mapper = new ObjectMapper();
            call =mapper.readValue(json, List.class);
        } catch (Exception e) {
            call = null;
        }
        return call;
    }

    @Override
    public String mapToFormat(Map<String, String> map) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(map);
    }

    @Override
    public String ListToFormat(List<Map<String, String>> list) throws JsonProcessingException {
        return null;
    }
}
