package ru.job4j.tracker;

public class Profession {
    private String name;
    private String company;
    private String educationLvl;

    public Profession() {
    }

    public void inputName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCompany(String name) {
        this.name = name;
    }

    public String getCompany() {
        return this.name;
    }
}