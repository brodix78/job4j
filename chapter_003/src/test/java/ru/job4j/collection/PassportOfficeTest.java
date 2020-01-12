package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PassportOfficeTest {

    @Test
    public void whenCitizenNotExist() {
        PassportOffice office = new PassportOffice();
        Citizen citizen = new Citizen("1234", "Vasya");
        assertThat(office.add(citizen), is(true));
        assertThat(office.findByPassport("1234"), is (citizen));
    }

    @Test
    public void whenCitizenExist() {
        PassportOffice office = new PassportOffice();
        Citizen citizen = new Citizen("1234", "Vasya");
        office.add(citizen);
        Citizen citizen2 = new Citizen("1234", "Kolya");
        assertThat(office.add(citizen2), is(false));
        assertThat(office.findByPassport("1234"), is (citizen));
    }

}
