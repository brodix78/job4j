package ru.job4j.condition;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class PointTest {
    public static double distance(int x1, int y1, int x2, int y2) {
        return Point.distance(x1, y1, x2, y2);
    }

    @Test
    public void poinTest() {
        int x1 = 5;
        int y1 = 5;
        int x2 = -5;
        int y2 = -5;
        double expected = Math.sqrt(200);
        double out = distance(x1, y1, x2, y2);
        Assert.assertEquals(expected, out,0.01);
    }
}
