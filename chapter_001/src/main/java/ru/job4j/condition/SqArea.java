package ru.job4j.condition;

public class SqArea {

    public static double square(int p, int k) {
        double height = p * 0.5 / (k + 1);
        return k * height * height;
    }

    public static void main(String[] args) {
        double result1 = square(6, 2);
        System.out.println(" p = 6, k = 2, real = " + result1);
    }
}
