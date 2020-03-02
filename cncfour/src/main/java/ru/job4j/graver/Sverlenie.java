package ru.job4j.graver;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Sverlenie extends Operation{

    private String fileOutput;
    private String spindle;
    private int turns;
    private float feed;
    private float Start;
    private float Finish;
    private float safeStart;
    private ArrayList<Float> points;

    protected void proG() {
        spindle = spindle.toLowerCase();
        try (BufferedWriter nc = new BufferedWriter(new FileWriter(new File(fileOutput)))){
            StringBuilder rsl = new StringBuilder();
            if ("4".equals(spindle)) {
                rsl.append(String.format("G0 Z%.3f%n G0 X0.%n", safeStart));
            } else if ("3".equals(spindle)) {
                rsl.append(String.format("G0 X0. Z%.3f%n", safeStart));
            } else if ("1g".equals(spindle)) {
                rsl.append(String.format("G0 X%.3f Y%.3f Z%.3f%n", points.get(0), points.get(1), safeStart));
            } else if ("1v".equals(spindle)) {
                rsl.append(String.format("G0 X%.3f Y%.3f Z-10.%n", safeStart, points.get(0)));
            } else if ("2g".equals(spindle)) {
                rsl.append(String.format("G0 Z%.3f%n G0 X%.3f%n", safeStart, points.get(0)));
            }else if ("2v".equals(spindle)) {
                rsl.append(String.format("G0 Z%.3f%n G0 X%.3f%n", points.get(0), safeStart));
            }
            rsl.append(generator());
            String[] fill = fillFromFile("SverSp" + spindle +".ncst");
            nc.write(fill[0]);

            nc.write(fill[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generator() {
        Iterator pointsIt = points.iterator();
        while (pointsIt.hasNext()) {
            StringBuilder rsl = new StringBuilder();
            if ("4".equals(spindle)) {
                rsl.append("G0 Z2.%n G0 X0.%n");
                rsl.append("G0 Z2.%n");
            }
            if ("3".equals(spindle)) {
                rsl.append("G0 X0. Z-2.%n");
                rsl.append("G0 Z-2.%n");
            }
            if ("1g".equals(spindle)) {
                rsl.append("G0 X0. Z-2.%n");
                rsl.append("G0 Z-2.%n");
            }
            if ("1v".equals(spindle)) {
                rsl.append("G0 X0. Z-2.%n");
                rsl.append("G0 Z-2.%n");
            }
            if ("1g".equals(spindle)) {
                rsl.append("G0 X0. Z-2.%n");
                rsl.append("G0 Z-2.%n");
            }

            if ("1v".equals(spindle)) {
                rsl.append("G0 X0. Z-2.%n");
                rsl.append("G0 Z-2.%n");
            }
        }
        return null;
    }
}