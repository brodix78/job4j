package ru.job4j.oop;

import java.awt.*;

public class Programmer extends Engineer {
    private boolean distanceJob;
    private String langList;

    public void taskExe(Customer customer) {
        TaskExe taskExe = new TaskExe(customer);
    }

    public void taskExe() {
        TaskExe taskExe = new TaskExe(this.getCompany());
    }
}
