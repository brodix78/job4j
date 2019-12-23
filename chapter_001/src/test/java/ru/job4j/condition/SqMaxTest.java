package ru.job4j.condition;

import org.junit.Test;

import java.beans.PropertyEditorSupport;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SqMaxTest {

    @Test
    public void whenFirstMax() {
        int result = SqMax.max(15, 5, 2, 8);
        assertThat(result, is(15));
    }

    @Test
    public void whenSecondMaxMax() {
        int result = SqMax.max(2, 8, 5, 6);
        assertThat(result, is(8));
    }

    @Test
    public void whenSecondThirdMax() {
        int result = SqMax.max(6, 8, 12, 10);
        assertThat(result, is(12));
    }

    @Test
    public void whenSecondForthMax() {
        int result = SqMax.max(4, 17, 12, 44);
        assertThat(result, is(44));
    }
}
