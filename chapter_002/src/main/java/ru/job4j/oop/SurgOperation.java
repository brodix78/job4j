package ru.job4j.oop;

public class SurgOperation {
    public Diagnose diagnose;
    public Pacient pacient;
    public long opDate;
    public String opResult;

    public SurgOperation(Diagnose diagnose) {
        this.diagnose = diagnose;
        this.pacient = diagnose.pacient;
    }

    public void ifPacientDied() {
    }
}
