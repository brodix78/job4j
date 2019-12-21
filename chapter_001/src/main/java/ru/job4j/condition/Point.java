package ru.job4j.condition;

public class Point {

    public static double distance(int x1, int y1, int x2, int y2) {
    return Math.pow(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2), 0.5);
    }

    public static void main(String[] args) {
        int x1 = 0;
        int y1 = 0;
        int x2 = 2;
        int y2 = 0;
        double result = distance(x1, y1, x2, y2);
        System.out.println("result (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ") " + result);
        x1 = 5;
        y1 = 10;
        x2 = -5;
        y2 = 20;
        result = distance(x1, y1, x2, y2);
        System.out.println("result (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ") " + result);
        x1 = 8;
        y1 = -110;
        x2 = 54;
        y2 = 0;
        result = distance(x1, y1, x2, y2);
        System.out.println("result (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ") " + result);
        x1 = 16;
        y1 = 36;
        x2 = 14;
        y2 = 188;
        result = distance(x1, y1, x2, y2);
        System.out.println("result (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ") " + result);
    }
}
