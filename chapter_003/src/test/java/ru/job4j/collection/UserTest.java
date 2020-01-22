package ru.job4j.collection;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void whenAsc() {
        List<User> users = Arrays.asList(
                new User("Petr", 32),
                new User("Ivan", 31)
        );
        Collections.sort(users);
        Iterator<User> it = users.iterator();
        assertThat(it.next(), is(new User("Ivan", 31)));
        assertThat(it.next(), is(new User("Petr", 32)));
    }

    @Test
    public void comparePetrVsIvan() {
        int rsl = new User("Petr", 32).compareTo(new User("Ivan", 31));
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void byName() {
        Comparator<User> compN = new UserSortByName();
        int rsl = compN.compare(new User("Ivan", 20), new User("Sergey", 21));
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void byNameRev() {
        Comparator<User> compNR = new UserSortByNameRev();
        int rsl = compNR.compare(new User("Ivan", 20), new User("Sergey", 21));
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void byAge() {
        Comparator<User> compA = new UserSortByAge();
        int rsl = compA.compare(new User("Ivan", 20), new User("Sergey", 21));
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void byAgeRev() {
        Comparator<User> compAR = new UserSortByAgeRev();
        int rsl = compAR.compare(new User("Ivan", 20), new User("Sergey", 21));
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void byNameAndAge() {
        Comparator<User> compNA = new UserSortByName().thenComparing(new UserSortByAge());
        int rsl = compNA.compare(new User("Ivan", 20), new User("Sergey", 21));
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void byNameAndAgeRev() {
        Comparator<User> compNRAR = new UserSortByNameRev().thenComparing(new UserSortByAgeRev());
        int rsl = compNRAR.compare(new User("Ivan", 20), new User("Sergey", 21));
        assertThat(rsl, greaterThan(0));
    }
}