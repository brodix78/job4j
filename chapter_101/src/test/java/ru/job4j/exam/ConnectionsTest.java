package ru.job4j.exam;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConnectionsTest {

    @Test
    public void thenAll() {
        String st = "111;123;222\n"
                + "200;123;100\n"
                + "300;;100";
        Connections con = new Connections();
        String exp = "Группа 1 содержит 3 элементов\n" +
                "111;123;222\n" +
                "200;123;100\n" +
                "300;;100\n";
        assertThat(con.groups(st), is(exp));
    }

    @Test
    public void thenTwoOfThree() {
        String st = "111;123;222\n"
                + "200;123;100\n"
                + "300;;200";
        Connections con = new Connections();
        String exp = "Группа 1 содержит 2 элементов\n" +
                "111;123;222\n" +
                "200;123;100\n" +
                "Группа 2 содержит 1 элементов\n" +
                "300;;200\n";
        assertThat(con.groups(st), is(exp));
    }
}