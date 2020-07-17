package ru.job4j.exam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.List;
import java.util.Map;

public class JsonConverter implements Converter{

    public List<Map<String, String>> formatToMaps(String json) {
        List<Map<String, String>> call;
        try {
            ObjectMapper mapper = new ObjectMapper();
            call = mapper.readValue(json, new TypeReference<List<Map<String,String>>>(){});
        } catch (Exception e) {
            call = null;
        }
        if (call == null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                call = List.of(mapper.readValue(json, new TypeReference<Map<String,String>>(){}));
            } catch (Exception e) {
                call = null;
            }
        }
        return call;
    }

    public String asFormat(Object object) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
