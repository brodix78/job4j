package ru.job4j.graver;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Sverlenie extends Operation{

    private String fileOutput;
    private String spindle;
    private float turns;
    private float feed;
    private float workDeep;
    private float start;
    private float deep;
    private float safeStart;
    private ArrayList<String> data;
    private ArrayList<Float[]> workSets = new ArrayList<>();
    LinkedHashMap<String, Float> workVariables = new LinkedHashMap<>();


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

    private String lines(String in, Float[] workSet) {
        int index = 0;
        for (String variable:workVariables.keySet()) {
            in = in.replace(workVariables.get(variable), String.format("%.3f", workSet[index]));
            index++;
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
            Float[] oneSet = new Float[workVariables.size()];
            int index = 0;
            for (String key:workVariables.keySet()){
                oneSet[index++] = lineToMap.containsKey(key) ? lineToMap.get(key) : workVariables.get(key);
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
        Float[] prevoius = null;
        for (Float[] workSet : workSets) {
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
                        ArrayList<String> wV = new ArrayList(workVariables.keySet());
                        int pos = wV.indexOf(rule.marker.substring(1));
                        if(workSet[pos] != prevoius[pos]) {
                            rsl.append(lines(rule.text, workSet));
                        }
                    }
                }
            }
            prevoius = workSet;
        }
        return rsl.toString();
    }
}