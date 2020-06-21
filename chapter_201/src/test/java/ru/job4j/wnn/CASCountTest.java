package ru.job4j.wnn;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CASCountTest {

    @Test
    public void whenIncrementThousandTimesWhenGetOneHundred() throws InterruptedException {
        CASCount count = new CASCount();
        for (int i = 0; i < 1000; i++) {
            new Thread(count::increment).start();
            new Thread(count::get).start();
        }
            Thread.sleep(100);
        assertThat(count.get(), is(1000));
    }
}