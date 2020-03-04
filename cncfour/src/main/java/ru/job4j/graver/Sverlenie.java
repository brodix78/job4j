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
    ArrayList<String> data;
    private ArrayList<Float[]> points = new ArrayList<>();

    public Sverlenie(String spindle, ArrayList<String> data){
        this.spindle = spindle;
        this.data = data;
    }

    String[] values = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight"};

    private String lines(String in, float[] attr) {
        int index = 1;
        for (Float att:attr) {
            in = in.replace(values[index++], String.format("%.3f",att));
        }
        return in;
    }

    private void setWorkValues(int index) {
        safeStart = points.get(index)[0];
        start = points.get(index)[1];
        deep = points.get(index)[2];
        turns = points.get(index)[3];
        feed = points.get(index)[4];
        workDeep = points.get(index)[5];
    }

    private ArrayList<String> rulesRead() {
        ArrayList<String> rules = new ArrayList<>();
        int blockFind = 0;
        try(BufferedReader in = new BufferedReader(new FileReader(new File("./graver/in/Sverlenie/data.rul")))) {
            String line;
            while ((line = in.readLine()) != null && blockFind != 2) {
                if (!line.isEmpty() && !line.startsWith("*"))
                    if (line.startsWith("spindle" + spindle)) {
                        blockFind = blockFind == 0 ? 1 : 2;
                    } else {
                        if (blockFind == 1) {
                            rules.add(line);
                        }
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rules;
    }

    private void pointsGen(String[] rules) {
        ArrayList<LinkedHashMap<String, Float>> pointRules = new ArrayList<>();
        for (String ruleLine:rules) {
            LinkedHashMap<String, Float> rl = new LinkedHashMap<String, Float>();
            Arrays.stream(ruleLine.split(" "))
                    .forEach(line -> {
                        String[] pair = line.split("=");
                        rl.put(pair[0], Float.parseFloat(pair[1].replace(",", ".")));
                    });
            pointRules.add(rl);
        }
        for (String dataLine:data) {
            HashMap<String, Float> lineToMap = new HashMap<>();
            Arrays.stream(dataLine.split(" "))
                    .forEach(line -> {
                        String[] pair = line.split("=");
                        lineToMap.put(pair[0], Float.parseFloat(pair[1].replace(",", ".")));
                    });
            for (LinkedHashMap<String, Float> dataForLine:pointRules) {
                Float[] point = new Float[dataForLine.size()];
                int index = 0;
                for (String key:dataForLine.keySet()){
                    point[index++] = lineToMap.containsKey(key) ? lineToMap.get(key) : dataForLine.get(key);
                }
                points.add(point);
            }
        }
    }

    protected String proG() {
        spindle = spindle.toLowerCase();
        setWorkValues(0);
        StringBuilder rsl = new StringBuilder();
        String[] fill = fillFromFile("./graver/Sverlenie/Sp" + spindle +".ncst");
        rsl.append(fill[0]);
        rsl.append(generator());
        rsl.append(fill[1]);
        return rsl.toString();
    }

    private String generator() {
        StringBuilder rsl = new StringBuilder();
        ArrayList<String> rules = rulesRead();
        pointsGen(new String[]{rules.get(5), rules.get(6)});
        int index = 0;
        while (index < points.size()) {
            Float[] coordinate = points.get(index);
            if (coordinate[3] != turns) {
                rsl.append(String.format(lines(rules.get(0), new float[]{coordinate[3]})));
            }
            if (coordinate[4] != feed) {
                rsl.append(String.format(lines(rules.get(1), new float[]{coordinate[4]})));
            }
            setWorkValues(index++);
            int type = index == 1 ? 2 : 3;
            coordinate = points.get(index++);
            rsl.append(String.format(lines(rules.get(type), new float[]{
                        safeStart, coordinate[0], coordinate[1], coordinate[2]})));
            rsl.append(String.format(lines(rules.get(4), new float[]{
                    safeStart, start, deep, workDeep * 2, workDeep })));
        }
        return rsl.toString();
    }
}