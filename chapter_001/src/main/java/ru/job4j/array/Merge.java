package ru.job4j.array;

public class Merge {
    @SuppressWarnings("checkstyle:EmptyBlock")
    public int[] merge(int[] left, int[] right) {
        int[] rsl = new int[left.length + right.length];
        int li = 0, ri = 0;
        for (int i = 0; i < rsl.length; i++) {
            if (li < left.length && ri < right.length) {
                rsl[i] = left[li] < right[ri] ? left[li++] : right[ri++];
            } else if (ri == right.length) {
                rsl[i] = left[li++];
            } else {
                rsl[i] = right[ri++];
            }
        }
        return rsl;
    }
}
