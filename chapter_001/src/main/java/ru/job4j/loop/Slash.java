package ru.job4j.loop;

public class Slash {

    public static void draw(int size) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                boolean left = x == y;
                boolean right = x + y == size - 1;
                if (left || right) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }

            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("Draw by 3");
        draw(3);
        System.out.println("Draw by 5");
        draw(5);
    }
}
