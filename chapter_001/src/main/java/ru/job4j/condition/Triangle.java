package ru.job4j.condition;

public class Triangle {
    private Point first;
    private Point second;
    private Point third;
    private double ab;
    private double bc;
    private double ac;

    public Triangle(Point ap, Point bp, Point cp) {
        this.first = ap;
        this.second = bp;
        this.third =cp;
        this.ab = this.first.distance(this.second);
        this.bc = this.second.distance(this.third);
        this.ac = this.first.distance(this.third);
    }

    public boolean exist() {
        return (((this.ab + this.ac) > this.ac) && ((this.ab + this.bc) > this.ac) &&
                ((this.ac + this.bc) > this.ab));
    }

    public double perimHalf() {
        double rsl = -1;
        if (this.exist()) {
            rsl = (this.ab + this.bc + this.ac) / 2;
        }
        return rsl;
    }

    public double area() {
        double rsl = -1;
        double p = this.perimHalf();
        if (p > 0) {
            rsl = Math.sqrt(p * (p - this.ab) * (p - this.bc) * (p - this.ac));
        }
        return rsl;
    }
}