package ru.job4j.search;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {

    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.putTask(new Task("low", 5));
        queue.putTask(new Task("urgent", 1));
        queue.putTask(new Task("middle", 3));
        assertThat(queue.takeTask().getDesc(), is ("urgent"));
    }
}
