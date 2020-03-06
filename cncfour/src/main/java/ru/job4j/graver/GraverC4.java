package ru.job4j.graver;

import java.io.*;
import java.util.HashMap;

public class GraverC4 extends Operation {

    HashMap<String, Float[]> book = new HashMap<>();
    String text;
    float height;
    float dia;
    float deep;
    float z;
    String fileOutput;


    public GraverC4(String text, float height, float dia, float deep, float z, String fileOutput) {
        this.text = text;
        this.height = height;
        this.dia = dia;
        this.deep = deep;
        this.z = z;
        this.fileOutput = fileOutput;
    }

    public static void main(String[] ars) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Текст: ");
        String text = sc.readLine();
        System.out.print("Высота, мм: ");
        float height = Float.parseFloat(sc.readLine().replaceAll(",", "."));
        System.out.print("Диаметр, мм: ");
        float dia = Float.parseFloat(sc.readLine().replaceAll(",", "."));
        System.out.print("Глубина гравировки, мм: ");
        float deep = Float.parseFloat(sc.readLine().replaceAll(",", "."));
        System.out.print("Расположение линии по Z, мм: ");
        float z = Float.parseFloat(sc.readLine().replaceAll(",", "."));
        GraverC4 p = new GraverC4(text, height, dia, deep, z, "./graver/PROG.WPD");
        p.proG();
    }

    protected void proG() {
        float lenFromStart = 0;
        float koef = (float) (360 / 3.14) / dia;
        StringBuilder prg = new StringBuilder();
        for(char ch:text.toCharArray()) {
            String sign = String.valueOf(ch);
            Float[] data;
            if (" ".equals(sign)) {
                lenFromStart += height * 0.60 * koef ;
            } else {
                String fileName = sign;
                Float broadKoef = (float) 1;
                if (fileName.equals(",")) {
                    broadKoef = broadKoef * (float) 2.5;
                    fileName = "comma";
                } else if (fileName.equals(".")) {
                    fileName = "point";
                    broadKoef = broadKoef * (float) 2.5;
                }
                try (ObjectInputStream dataIn = new ObjectInputStream(new FileInputStream(String.format("./graver/book/%s.ar", fileName)))){
                    if (!book.containsKey(sign)) {
                        data = (Float[]) dataIn.readObject();
                        for (int i = 0; i < data.length; i++) {
                            data[i] = data[i] / (float) 8.5 * height;
                        }
                        data[0] = data[0] * broadKoef;
                        book.put(sign, data);
                    } else data = book.get(sign);
                    int index = 4;
                    prg.append(String.format("   G0 C4=%.3f Z%.3f%n", data[2] * koef + lenFromStart, data[3] + z));
                    prg.append(String.format("   G1 X%.3f F0.1%n", dia - 2 * deep ));
                    while (index < data.length) {
                        prg.append(String.format("   G1 C4=%.3f Z%.3f%n", data[index++] * koef + lenFromStart, data[index++] + z));
                    }
                    prg.append(String.format("   G0 X%.3f%n", dia + 1.2));
                    lenFromStart += (height * 0.15  + data[0]) * koef;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try (FileWriter out = new FileWriter(new File(fileOutput))) {
            String[] fill = fillFromFile("C4Z.ncst");
            out.write(fill[0]);
            out.write(String.format("   G0 Z%.3f C4=0%n   G0 X%.3f%n", z, dia + 2).replace(",", "."));
            out.write(prg.toString().replace(",", "."));
            out.write(fill[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}