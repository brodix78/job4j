package ru.job4j.exam;


import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PriceTest {

    public Price price(String in) throws ParseException {
        String[] data = in.split("_");
        Price price = new Price();
        price.product_code = data[0];
        price.number = Integer.parseInt(data[1]);
        price.depart = Integer.parseInt(data[2]);
        price.begin = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(data[3]);
        price.end = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(data[4]);
        price.value = Long.parseLong(data[5]);
        return price;
    }

    @Test
    public void fromTask() throws ParseException {
        List<Price> current = List.of(
                price("122856_1_1_01.01.2013 00:00:00_31.01.2013 23:59:59_11000"),
                price("122856_2_1_10.01.2013 00:00:00_20.01.2013 23:59:59_99000"),
                price("6654_1_2_01.01.2013 00:00:00_31.01.2013 00:00:00_5000")
                );
        List<Price> changes = List.of(
                price("122856_1_1_20.01.2013 00:00:00_20.02.2013 23:59:59_11000"),
                price("122856_2_1_15.01.2013 00:00:00_25.01.2013 23:59:59_92000"),
                price("6654_1_2_12.01.2013 00:00:00_13.01.2013 00:00:00_4000")
        );
        List<Price> expected = List.of(
                price("122856_1_1_01.01.2013 00:00:00_20.02.2013 23:59:59_11000"),
                price("122856_2_1_10.01.2013 00:00:00_15.01.2013 00:00:00_99000"),
                price("122856_2_1_15.01.2013 00:00:00_25.01.2013 23:59:59_92000"),
                price("6654_1_2_01.01.2013 00:00:00_12.01.2013 00:00:00_5000"),
                price("6654_1_2_12.01.2013 00:00:00_13.01.2013 00:00:00_4000"),
                price("6654_1_2_13.01.2013 00:00:00_31.01.2013 00:00:00_5000")
        );
        Prices pr = new Prices();
        List<Price> rsl = pr.prices(current, changes);
        assertThat(rsl.size(), is(6));
        for (Price price:rsl) {
           assertThat(expected.contains(price), is(true));
        }
    }
}