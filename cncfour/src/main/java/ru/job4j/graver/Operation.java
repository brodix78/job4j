package ru.job4j.graver;

import java.io.*;
import java.util.*;

public class Operation {

    private String spindle;
    private ArrayList<String> data;
    private ArrayList<HashMap<String, String>> workSets = new ArrayList<>();
    private HashMap<String, String> workVariables = new HashMap<>();
    private String message;

    protected class Rule {
        String text;
        String marker;

        private Rule(String text, String marker) {
            this.text = text;
            this.marker = marker;
        }
    }

    public Operation(String spindle, ArrayList<String> data) {
        this.spindle = spindle;
        this.data = data;
    }

    private String lines(String in, HashMap<String, String> workSet) {
        for (String variable:workSet.keySet()) {
            in = in.replace(variable, workSet.get(variable));
        }
        return in;
    }

    private ArrayList<Rule> rulesRead() {
        ArrayList<Rule> rules = new ArrayList<>();
        int blockFind = 0;
        try(BufferedReader in = new BufferedReader(new FileReader(new File("./graver/processing/drill.rul")))) {
            String line;
            while ((line = in.readLine()) != null && blockFind != 2) {
                if (!line.isEmpty() && !line.startsWith("*"))
                    if (line.startsWith("spindle" + spindle)) {
                        blockFind = blockFind == 0 ? 1 : 2;
                    } else {
                        if (blockFind == 1) {
                            if (line.startsWith("v")) {  //чтение видов переменных
                                pointsGen(line.substring(2));
                            } else if (line.startsWith("m")) {  //сообщение о типе операции
                                message = line.substring(2);
                            } else {
                                rules.add(new Rule(line.substring(1), line.substring(0, line.indexOf(" "))));
                            }
                        }
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rules;
    }

    private void pointsGen(String rule) {
        Arrays.stream(rule.split(" "))
                    .forEach(line -> {
                        String[] pair = line.split("=");
                        workVariables.put(pair[0], pair[1].replace(",", "."));
                    });
        for (String dataLine:data) {
            HashMap<String, String> lineToMap = new HashMap<>();
            Arrays.stream(dataLine.split(" "))
                    .filter(val -> !val.isEmpty() && val.contains("=")
                            && !val.startsWith("=") && !val.endsWith("="))
                    .forEach(line -> {
                        String[] pair = line.split("=");
                        lineToMap.put(pair[0], pair[1].replace(",", "."));
                    });
            HashMap<String, String > oneSet = new HashMap<>();
            for (String key:workVariables.keySet()){
                if (lineToMap.containsKey(key)) {
                    oneSet.put(key, lineToMap.get(key));
                } else {
                    oneSet.put(key, workVariables.get(key));
                }
            }
            workSets.add(oneSet);
        }
    }

    protected String proG() {
        spindle = spindle.toLowerCase();
        StringBuilder rsl = new StringBuilder();
        String[] fill = fillPatternFromFile();
        String main = generator();
        rsl.append("MSG (" + message + ")");
        rsl.append(fill[0]);
        rsl.append(main);
        rsl.append(fill[1]);
        return rsl.toString();
    }

    private String generator() {
        StringBuilder rsl = new StringBuilder();
        ArrayList<Rule> rules = rulesRead();
        int index = 0;
        HashMap<String, String> previous = null;
        for (HashMap<String, String> workSet : workSets) {
            index++;
            for (Rule rule : rules) {
                if ("r".equals(rule.marker)) {
                    rsl.append(lines(rule.text, workSet));
                } else if ("s".equals(rule.marker) && index > 1) {
                    rsl.append(lines(rule.text, workSet));
                } else if ("f".equals(rule.marker) && index == 1) {
                    rsl.append(lines(rule.text, workSet));
                } else if(rule.marker.startsWith("p")) {
                    if (index == 1) {
                        rsl.append(lines(rule.text, workSet));
                    } else {
                        String variable = (rule.marker.substring(1));
                        if(!workSet.get(variable).equals(previous.get(variable))) {
                            rsl.append(lines(rule.text, workSet));
                        }
                    }
                } else if ("l".equals(rule.marker) && index == workSets.size()) {
                    rsl.append(lines(rule.text, workSet));
                }
            }
            previous = workSet;
        }
        return rsl.toString();
    }

    protected String[] fillPatternFromFile(){
        String[] rsl = new String[2];
        try (BufferedReader nc = new BufferedReader(
                new FileReader(new File("./graver/processing/Sp" + spindle +".ncst")))){
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