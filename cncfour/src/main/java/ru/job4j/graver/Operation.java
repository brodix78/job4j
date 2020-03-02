package ru.job4j.graver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Operation {

    protected String[] fillFromFile(String library){
        String[] rsl = new String[2];
        try (BufferedReader nc = new BufferedReader(
                new FileReader(new File("./graver/in/" + library)))){
            int index = 0;
            String line;
            StringBuilder st = new StringBuilder();
            while ((line = nc.readLine()) != null) {
                if ("End".equals(line)) {
                    rsl[index] = st.toString();
                    st.setLength(0);
                    index++;
                } else {
                    st.append(line);
                    st.append(System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

}
