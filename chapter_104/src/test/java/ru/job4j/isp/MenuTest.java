package ru.job4j.isp;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuTest {

    public Menu menu;

    @Before
    public void init() {
        menu = new Menu("The menu", "  ");
        Item one = new Item("one", "");
        one.addKid("one.one", "first");
        Item two = new Item("two", "");
        Item three = new Item("three", "");
        Item threeOne = new Item("three.one", "");
        threeOne.addKid("three.one.one", "");
        three.addKid(threeOne);
        menu.addKid(one);
        menu.addKid(two);
        menu.addKid(three);
    }

    @Test
    public void menuTest () {
        String expect = null;
        try {
            expect = new String(Files.readAllBytes(Paths.get("./diffrent/menu.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(menu.menu(), is(expect));
    }

    @Test
    public void actionTest () {
        String expect = null;
        try {
            expect = new String(Files.readAllBytes(Paths.get("./diffrent/menu.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(menu.actionSelect("1.1"), is("first"));
    }

    @Test
    public void mein() {
       // PrintWriter out = new PrintWriter(System.out, true);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String ot = null;
            ot = in.readLine();
            System.out.println(ot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}