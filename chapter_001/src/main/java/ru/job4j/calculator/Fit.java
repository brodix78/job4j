package ru.job4j.calculator;

public class Fit {

    public static double manWeight(double height) {
        return (height - 100) * 1.15;
    }

    public static double womanWeight(double height) {
        return (height - 110) * 1.15;
    }

    public static void main(String[] args) {
        double man = manWeight(180);
        System.out.println("Man 180cm ideal weight is " + man + "kg");
        double woman = womanWeight(170);
        System.out.println("Woman 170cm ideal weight is " + woman + "kg");
    }
}
