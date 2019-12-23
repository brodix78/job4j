package ru.job4j.converter;

public class Converter {

    public static int rubEuro(int value) {
        return value / 70;
    }

    public static int rubDollar(int value) {
        return value / 60;
    }

    public static int euroRub(int valueEuro) {
        return valueEuro * 70;
    }

    public static int dollarRub(int valueDollar) {
        return valueDollar * 60;
    }

    public static void main(String[] args) {
        int euro = rubEuro(140);
        System.out.println("140 rubles are " + euro + " euros");
        int dollar = rubDollar(180);
        System.out.println("180 rubles are " + dollar + " dollars");
        int ruble = euroRub(5);
        System.out.println("5 euros are " + ruble + " rubles");
        ruble = dollarRub(7);
        System.out.println("7 dollars are " + ruble + " rubles");
    }
}


