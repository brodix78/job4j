package ru.job4j.calculator;
import org.junit.Assert;
import org.junit.Test;

public class FitTest {

    public static double manWeight(double height) {
        return Fit.manWeight(height);
    }

    public static double womanWeight(double height) {
        return Fit.womanWeight(height);
    }

    @Test
    public void manWeightTest() {
        double in = 180;
        double expected = 92;
        double out = manWeight(in);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void womanWeightTest() {
        double in = 170;
        double expected = 69;
        double out = womanWeight(in);
        Assert.assertEquals(expected, out, 0.01);
    }
}
