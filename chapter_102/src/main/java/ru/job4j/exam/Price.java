package ru.job4j.exam;

import java.util.Date;
import java.util.Objects;

public class Price {

    long id;
    String product_code;
    int number;
    int depart;
    Date begin;
    Date end;
    long value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return  number == price.number &&
                depart == price.depart &&
                value == price.value &&
                Objects.equals(product_code, price.product_code) &&
                Objects.equals(begin, price.begin) &&
                Objects.equals(end, price.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_code, number, depart, begin, end, value);
    }
}


