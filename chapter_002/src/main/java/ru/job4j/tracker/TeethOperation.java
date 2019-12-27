package ru.job4j.tracker;

public class TeethOperation extends SurgOperation {
    public String opType;
    public int opPrice;

    public TeethOperation(Diagnose diagnose) {
        super(diagnose);
    }
}