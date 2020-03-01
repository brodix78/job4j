package ru.job4j.graver;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class ProgCNC {

    HashMap<String, Float[]> book = new HashMap<>();
    String text;
    float height;
    float dia;
    float deep;

    public ProgCNC(String text, float height, float dia, float deep) {
        this.text = text;
        this.height = height;
        this.dia = dia;
        this.deep = deep;
    }

    public static void main(String[] ars) {
        Scanner scanner =  new Scanner(System.in);
        System.out.print("Текст: ");
        String text = scanner.next();
        System.out.print("Высота, мм: ");
        float height = Float.parseFloat(scanner.next().replaceAll(",", "."));
        System.out.print("Диаметр, мм: ");
        float dia = Float.parseFloat(scanner.next().replaceAll(",", "."));
        System.out.print("Глубина гравировки, мм: ");
        float deep = Float.parseFloat(scanner.next().replaceAll(",", "."));
        ProgCNC p = new ProgCNC(text, height, dia, deep);
        p.proG();
    }

    private void proG() {
        for(char ch:text.toCharArray()) {
            String sign = String.valueOf(ch);
            try (ObjectInputStream dataIn = new ObjectInputStream(new FileInputStream(String.format("./graver/book/%s.ar", sign)))){
                Float[] data;
                if (!book.containsKey(sign)) {
                    data = (Float[]) dataIn.readObject();
                    for (int i = 0; i < data.length; i++) {
                        System.out.print(String.format("%.3f%n", data[i]).replace(",", "."));
                        data[i] = data[i] / (float) 8.5 * height;
                    }
                    book.put(sign, data);
                } else data = book.get(sign);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
