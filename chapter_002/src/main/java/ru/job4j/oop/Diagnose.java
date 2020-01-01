package ru.job4j.oop;

public class Diagnose {
    public long date;
    public String medTest;
    public String symptoms;
    public String diagnose;
    public Pacient pacient;

    public Diagnose(Pacient pacient) {
        this.pacient = pacient;
    }
}
