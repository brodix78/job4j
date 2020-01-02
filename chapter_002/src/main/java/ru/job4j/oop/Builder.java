package ru.job4j.oop;

public class Builder extends Profession {

    public String special;
    public int stage;

    public Builder(String type) {
        this.special = type;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getStage() {
        return this.stage;
    }

    public void makeObject() {
        BuildJob buildJob = new BuildJob();
    };
}