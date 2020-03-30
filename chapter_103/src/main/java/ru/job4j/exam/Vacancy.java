package ru.job4j.exam;

public class Vacancy {
    public String name;
    public String text;
    public String link;

    public Vacancy(String head, String text, String link) {
        this.name = head;
        this.text = text;
        this.link = link;
    }
}