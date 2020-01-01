package ru.job4j.oop;

public class Surgeon extends Doctor {

    public Surgeon() {
    }

    public void makeOperation(Diagnose diagnose) {
        SurgOperation operation = new SurgOperation(diagnose);
    }

}
