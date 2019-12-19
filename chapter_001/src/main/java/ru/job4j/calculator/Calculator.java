package ru.job4j.calculator;

/**
 *Calculator
 *
 * @athor Ilya Brodnikov
 */

public class Calculator {

    /**
     * Method add
     * @param first and second numbers
     * @return sum of numbers
     */
    public static void add(int first, int second) {
        int result = first + second;
        System.out.println(first + " + " + second + " = " + result);
    }

    /**
     * Method div
     * @param first and second numbers
     * @return quotient of numbers
     */
    public static void div(int first, int second) {
        int result = first / second;
        System.out.println(first + " / " + second + " = " + result);
    }

    /**
     * Method multiply
     * @param first and second numbers
     * @return product of numbers
     */
    public static void multiply(int first, int second) {
        int result = first * second;
        System.out.println(first + " * " + second + " = " + result);
    }

    /**
     * Method subtract
     * @param first and second numbers
     * @return difference of numbers
     */
    public static void subtract(int first, int second) {
        int result = first - second;
        System.out.println(first + " - " + second + " = " + result);
    }

    /**
     * Calculation with two numbers
     * @param args
     */
    public static void main(String[] args) {
        add(1, 1);
        div(4, 2);
        multiply(2, 1);
        subtract(10, 5);
    }
}
