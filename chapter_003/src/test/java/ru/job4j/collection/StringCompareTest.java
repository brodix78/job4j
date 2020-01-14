package ru.job4j.collection;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StringCompareTest {
    @Test
    public void whenStringsAreEqualThenZero() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Ivanov",
                "Ivanov"
        );
        assertThat(rst, is(0));
    }

    @Test
    public void whenRightEmptyThenShouldBePositive() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Ivanov",
                ""
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void whenLeftEmptyThenShouldBeNegative() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "",
                "Petrov"
        );
        assertThat(rst, lessThan(0));
    }

    @Test
    public void whenLeftLessThanRightResultShouldBeNegative() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Ivanov",
                "Ivanova"
        );
        assertThat(rst, lessThan(0));
    }

    @Test
    public void whenLeftGreaterThanRightResultShouldBePositive() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Petrov",
                "Ivanova"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Petrov",
                "Patrov"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Patrova",
                "Petrov"
        );
        assertThat(rst, lessThan(0));
    }

    @Test
    public void whenEqualAndLeftEndsWithSpacesThenShouldBePositive() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Ivanov    ",
                "Ivanov"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void whenEqualAndLeftStartsWithSpacesThenShouldBeNegative() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "  Ivanov",
                "Ivanov"
        );
        assertThat(rst, lessThan(0));
    }

    @Test
    public void whenVsStringCompare() {
        StringCompare compare = new StringCompare();
        String[] testWords = {"aa", "aaaaaaaa",
                " ZZZ", "ZZZZ",
                "Test", "TesT",
                "guesttttt", "guest",
        };
        int rst = compare.compare(
                "  Ivanov",
                "Ivanov"
        );
        int i = 0;
        do {
            assertThat(compare.compare(testWords[i], testWords[i + 1]), is(testWords[i].compareTo(testWords[++i])));
            i++;
        } while (i <testWords.length);
    }
}