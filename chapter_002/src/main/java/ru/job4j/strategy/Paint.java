package ru.job4j.strategy;

public class Paint {
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args) {
        Square sq = new Square();
        new Paint().draw(sq);
        Triangle tr = new Triangle();
        new Paint().draw(tr);
    }
}
