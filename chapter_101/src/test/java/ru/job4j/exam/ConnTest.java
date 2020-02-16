package ru.job4j.exam;

import org.junit.Test;

public class ConnTest {

    @Test
    public void taramparam() {
        String st = ";;1\n"
                + "65;15;92\n"
                + "80;48;92\n"
                + "111;123;222\n"
                + "200;123;100\n"
                + "300;;100\n"
                + "50;;80\n"
                + "100;123;80\n"
                + "400;45;66\n"
                + "20;12;13\n"
                + "300;415;100";
        String st5 = ";;;;1\n"
                + "12;34;65;15;92\n"
                + "18;88;80;48;92\n"
                + "698;68;111;123;222\n"
                + "656;56;200;123;100\n"
                + "656;80;300;;100\n"
                + ";;50;;80\n"
                + "88;46;100;123;80\n"
                + ";689;400;45;66\n"
                + "24;689;20;12;13\n"
                + "33;;300;415;100";
        String stEmpty = "";
        Conn con = new Conn();
        System.out.println("st в строке 3 элемента" + "\n" + con.groups(st));
        System.out.println("st5 в строке 5 элементов" + "\n" + con.groups(st5));
        System.out.println("stEmpty" + "\n" + con.groups(stEmpty));
    }
}