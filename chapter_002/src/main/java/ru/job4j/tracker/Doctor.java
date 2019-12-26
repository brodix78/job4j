package ru.job4j.tracker;

public class Doctor extends Profession {

    public Doctor() {
    }

    public Diagnose Heal(Pacient pacient) {
        Diagnose diagnose = new Diagnose();
        return diagnose;
    }
}
