package ru.job4j.oop;

public class Dentist extends Doctor {
    public boolean privatePractice;

    public void teethOperation(Diagnose diagnose) {
        TeethOperation teethOperation = new TeethOperation(diagnose);
    };
}
