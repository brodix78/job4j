package ru.job4j.srp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class JSONReport implements Generator {

    @Override
    public String report(List<Employee> employee) {
        String report = null;
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.setDateFormat(df);
        try {
            report = objMapper.writeValueAsString(employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return report;
    }
}