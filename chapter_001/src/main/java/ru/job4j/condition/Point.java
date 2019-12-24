package ru.job4j.condition;

public class Point {

    private int x;

    private int y;

    public Point(int first, int second) {
        this.x = first;
        this.y = second;
    }

    public double distance(Point second) {
    return Math.pow(Math.pow(this.x - second.x, 2) + Math.pow(this.y - second.y, 2), 0.5);
    }

    public void showDist(Point second) {
        double dist = this.distance(second);
        System.out.println("result (" + this.x + ", " + this.y + ") to (" + second.x + ", " + second.y + ") " + dist);
    }

    public static void main(String[] args) {
        Point a = new Point(5, 10), b = new Point(-5, 10);
        a.showDist(b);
    }
}