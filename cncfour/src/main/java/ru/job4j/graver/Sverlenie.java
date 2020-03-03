package ru.job4j.graver;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
    /     координата 1, координата 2, координата 3 (если используется):
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

    private HashMap<String, ArrayList<String>> libSp() {
        /*  0. Обороты шпинделя
        /   1. Подача
        /   2. Безопасный первый подход
        /   3. Последующий подход
        /   4. Сверление
         */
        HashMap<String, ArrayList<String>> lib = new HashMap<>();
        try(BufferedReader in = new BufferedReader(new FileReader(new File("./in/Sverlenie/data.rul")))) {
            String line;
            while ((line = in.readLine()) != null){
                ArrayList<String> data = new ArrayList(Arrays.asList(line.split("@@")));
                String key = data.get(0);
                data.remove(0);
                lib.put(key, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lib;
    }

    protected void proG() {
        spindle = spindle.toLowerCase();
        try (BufferedWriter nc = new BufferedWriter(new FileWriter(new File(fileOutput)))){
            setWorkValues(0);
            StringBuilder rsl = new StringBuilder();
            rsl.append(generator());
            String[] fill = fillFromFile("/Sverlenie/Sp" + spindle +".ncst");
            nc.write(fill[0]);
            nc.write(rsl.toString());
            nc.write(fill[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generator() {
        StringBuilder rsl = new StringBuilder();
        HashMap<String, ArrayList<String>> lib = libSp();
        int index = 0;
        while (index < points.size()) {
            Float[] line = points.get(index);
            if (line[3] != turns) {
                rsl.append(String.format(lines(lib.get(spindle).get(0), new float[]{line[3]})));
            }
            if (line[4] != feed) {
                rsl.append(String.format(lines(lib.get(spindle).get(1), new float[]{line[4]})));
            }
            setWorkValues(index++);
            int type = index == 1 ? 2 : 3;
            line = points.get(index++);
            rsl.append(String.format(lines(lib.get(spindle).get(type), new float[]{
                        safeStart, line[0], line[1], line[2]})));
            rsl.append(String.format(lines(lib.get(spindle).get(3), new float[]{
                    safeStart, start, deep, workDeep * 2, workDeep })));
        }
        return rsl.toString();
    }
}