package ru.job4j.search;

import java.util.ArrayList;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        var find = new ArrayList<Person>();
        for (Person someone : this.persons) {
            if (checkContain(someone, key)) {
                find.add(someone);
            }
        }
        return find;
    }

    private boolean checkContain(Person person, String key) {
        return person.getName().contains(key) || person.getSurname().contains(key)
                || person.getPhone().contains(key) || person.getAddress().contains(key);
    }
}