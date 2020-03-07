package ru.job4j.graver;

import java.io.*;
import java.util.*;

public class Sverlenie extends Operation{

    private String fileOutput;
    private String spindle;
    private ArrayList<String> data;
    private ArrayList<HashMap<String, Float>> workSets = new ArrayList<>();
    HashMap<String, Float> workVariables = new HashMap<>();


    public Sverlenie(String spindle, ArrayList<String> data){
        this.spindle = spindle;
        this.data = data;
    }

    protected class Rule {
        String text;
        String marker;

        private Rule(String text, String marker) {
            this.text = text;
            this.marker = marker;
        }
    }

    private String lines(String in, HashMap<String, Float> workSet) {
        for (String variable:workSet.keySet()) {
            in = in.replace(variable, String.format("%.3f", workSet.get(variable)));
        }
        return in;
    }

    private ArrayList<Rule> rulesRead() {
        ArrayList<Rule> rules = new ArrayList<>();
        int blockFind = 0;
        try(BufferedReader in = new BufferedReader(new FileReader(new File("./graver/in/Sverlenie/data.rul")))) {
            String line;
            while ((line = in.readLine()) != null && blockFind != 2) {
                if (!line.isEmpty() && !line.startsWith("*"))
                    if (line.startsWith("spindle" + spindle)) {
                        blockFind = blockFind == 0 ? 1 : 2;
                    } else {
                        if (blockFind == 1) {
                            if (line.startsWith("v")) {  //чтение видов переменных
                                pointsGen(line.substring(2));
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
                        workVariables.put(pair[0], Float.parseFloat(pair[1].replace(",", ".")));
                    });
        for (String dataLine:data) {
            HashMap<String, Float> lineToMap = new HashMap<>();
            Arrays.stream(dataLine.split(" "))
                    .filter(val -> !val.isEmpty() && val.contains("=")
                            && !val.startsWith("=") && !val.endsWith("="))
                    .forEach(line -> {
                        String[] pair = line.split("=");
                        lineToMap.put(pair[0], Float.parseFloat(pair[1].replace(",", ".")));
                    });
            HashMap<String, Float> oneSet = new HashMap<>();
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
        String[] fill = fillFromFile("./graver/Sverlenie/Sp" + spindle +".ncst");
        rsl.append(fill[0]);
        rsl.append(generator());
        rsl.append(fill[1]);
        return rsl.toString();
    }

    private String generator() {
        StringBuilder rsl = new StringBuilder();
        ArrayList<Rule> rules = rulesRead();
        int index = 0;
        HashMap<String, Float> previous = null;
        for (HashMap<String,Float> workSet : workSets) {
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
                        if(workSet.get(variable) != previous.get(variable)) {
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
}