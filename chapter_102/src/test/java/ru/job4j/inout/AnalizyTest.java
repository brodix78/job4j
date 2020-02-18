package ru.job4j.inout;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Test
    public void simplyTesting() {
        String log = "./src/main/java/ru/job4j/inout/temp/sys.log";
        String unv = "./src/main/java/ru/job4j/inout/temp/unavailable.csv";
        try (PrintWriter out = new PrintWriter(new FileOutputStream(log))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(log, unv);
        try (BufferedReader in = new BufferedReader(new FileReader(unv))) {
            assertThat(in.readLine(), is("10:57:01;10:59:01"));
            assertThat(in.readLine(), is("11:01:02;11:02:02"));
            Assert.assertNull(in.readLine());
        } catch (Exception om) {
            om.printStackTrace();
        }
    }
}

