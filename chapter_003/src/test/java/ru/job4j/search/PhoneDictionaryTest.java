package ru.job4j.search;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import java.util.ArrayList;

public class PhoneDictionaryTest {
    @Test
    public void whenFindName() {
        var dictionary = new PhoneDictionary();
        dictionary.add(new Person("Michael", "Jackson", "12345678", "Neverland"));
        dictionary.add(new Person("Ivan", "Ivanov", "12345678", "Ivanovo"));
        var find = dictionary.find("acks");
        assertThat(find.get(0).getName(), is("Michael"));
        assertThat(find.size(), is(1));
    }
}