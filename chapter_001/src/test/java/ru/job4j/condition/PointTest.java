package ru.job4j.condition;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.sql.PseudoColumnUsage;

public class PointTest {
    public double distance(Point second) {
        return this.distance(second);
    }

    @Test
    public void poinTest() {
        Point a = new Point(5, 5), b = new Point(-5, -5);
        double expected = Math.sqrt(200);
        double out = a.distance(b);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void poinTest3d() {
        Point a = new Point(5, 5, 5), b = new Point(-5, -5, -5);
        double expected = Math.sqrt(300);
        double out = a.distance3d(b);
        Assert.assertEquals(expected, out, 0.01);
    }
}
