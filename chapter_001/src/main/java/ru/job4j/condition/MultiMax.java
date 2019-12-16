package ru.job4j.condition;

public class MultiMax {
    public int max(int first, int second, int third) {
        int result = first;
        if (second > first) {
            result = second;
        }
        if (third > result) {
            result = third;
        }
        return result;
    }
}
