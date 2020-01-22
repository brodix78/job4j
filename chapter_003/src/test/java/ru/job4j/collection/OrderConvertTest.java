package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OrderConvertTest {
    @Test
    public void whenOneOrder() {
        OrderConvert conv = new OrderConvert();
        List<Order> input = List.of(
                new Order("546", "Find something")
        );
        assertThat(conv.process(input).get("546"), is(new Order("546", "Find something")));
    }
}
