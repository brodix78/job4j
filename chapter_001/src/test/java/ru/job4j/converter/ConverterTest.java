package ru.job4j.converter;

import org.junit.Assert;
import org.junit.Test;

public class ConverterTest {

    @Test
    public void rubleToEuro() {
        int in = 140;
        int expected = 2;
        int out = Converter.rubEuro(in);
        Assert.assertEquals(expected, out);
    }

    @Test
    public void rubleToDollar() {
        int in = 180;
        int expected = 3;
        int out = Converter.rubDollar(in);
        Assert.assertEquals(expected, out);
       }

    @Test
    public void euroToRuble() {
        int in = 5;
        int expected = 350;
        int out = Converter.euroRub(in);
        Assert.assertEquals(expected, out);
    }

    @Test
    public  void dollarToRuble() {
        int in = 7;
        int expected = 420;
        int out = Converter.dollarRub(in);
        Assert.assertEquals(expected, out);
    }
}