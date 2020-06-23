package ru.job4j.nba;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

public class BaseCacheTest {

    @Test
    public void whenTryingPutSameVersions() throws InterruptedException {
        BaseCache cache = new BaseCache();
        cache.add(new Base(1));
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread one = new Thread(() -> {
            try {
                cache.update(new Base(1));
            } catch (OptimisticException e) {
                ex.set(e);
            }
        });
        Thread two = new Thread(() -> {
            try {
                cache.update(new Base(1));
            } catch (OptimisticException e) {
                ex.set(e);
            }
        });
        one.start();
        two.start();
        one.join();
        two.join();
        assertThat(ex.get().getMessage(), is("Versions collision"));
    }
}
