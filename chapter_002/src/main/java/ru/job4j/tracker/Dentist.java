package ru.job4j.tracker;

public class Dentist extends Doctor {
    public boolean privatePractice;

    public void teethOperation(Diagnose diagnose){
        TeethOperation teethOperation =new TeethOperation(diagnose);
    };
}
