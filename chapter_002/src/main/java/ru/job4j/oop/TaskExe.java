package ru.job4j.oop;

import java.beans.Customizer;

public class TaskExe {
    public String techNotes;
    public long startDate;
    public long closeDare;
    public String report;
    public String jobController;

    public TaskExe(Customer customer) {
        this.jobController = customer.name;
    }

    public TaskExe(String company) {
        this.jobController = company;
    }
}
