package ru.job4j.xZero;

import org.junit.Test;

public class TextInOutTest {

    @Test
    public void fieldOutTest() {
        Field field = new Field(100);
        TextInOut tx = new TextInOut();
        tx.showField(field);
    }
}