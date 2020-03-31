package ru.job4j.exam;

import java.util.Objects;

public class Vacancy {
    public String name;
    public String text;
    public String link;

    public Vacancy(String head, String text, String link) {
        this.name = head;
        this.text = text;
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return name.equals(vacancy.name) &&
                text.equals(vacancy.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, text);
    }
}