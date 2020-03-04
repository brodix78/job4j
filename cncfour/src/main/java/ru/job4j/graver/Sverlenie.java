package ru.job4j.graver;

import java.io.*;
import java.util.ArrayList;

public class Sverlenie extends Operation{

    private String fileOutput;
    private String spindle;
    private float turns;
    private float feed;
    private float workDeep;
    private float start;
    private float deep;
    private float safeStart;
    private ArrayList<Float[]> points;
    /*  Последовательно значения в массиве:
    /   Четные элементы (начиная с 0)
    /     0. Координата безопасного подхода-отхода по оси сверления;
    /     1. Координата начала сверления;
    /     2. Координата начала сверления;
    /     3. Обороты шпинделя;
    /     4. Подача мм/оборт;
    /     5. Подача мм на один заход;
    /   Нечетные элементы:
    /     координата 1, координата 2, координата 3 (значение 0 если используется):
    /       Для шпинделя 3,4 не указывается в массиве торчит один 0;
    /       Для шпинделя 1g пара X, Y, C3 (горизонтальный приводной инструмент в верхней башке)
    /       Для шпинделя 2g пара X, C4 (горизонтальный приводной инструмент в нижней башке)
    /       Для шпинделя 1v пара Y, Z, C3 (вертикальный приводной инструмент в верхней башке)
    /       Для шпинделя 2v пара Z, C4 (вертикальный приводной инструмент в нижней башке)
   */
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
        /*  0. Обороты шпинделя
        /   1. Подача
        /   2. Безопасный первый подход
        /   3. Последующий подход
        /   4. Сверление
         */
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
            rsl.append(String.format(lines(rules.get(3), new float[]{
                    safeStart, start, deep, workDeep * 2, workDeep })));
        }
        return rsl.toString();
    }
}