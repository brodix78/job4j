package ru.job4j.oop;

public class Doctor extends Profession {

    public Doctor() {
    }

    public Diagnose Heal(Pacient pacient) {
        Diagnose diagnose = new Diagnose(pacient);
        return diagnose;
    }
}
