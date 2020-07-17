package ru.job4j.exam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonConverter<T> implements Converter<T>{

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
    public String toFormat(List<T> items) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(items);
    }

}
