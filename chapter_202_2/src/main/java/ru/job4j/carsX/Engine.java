package ru.job4j.carsX;

import javax.persistence.*;
import java.util.Objects;

public class Engine {

    private int id;

    private String model;

    public static Engine of(String model) {
        Engine engine = new Engine();
        engine.model = model;
        return engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return id == engine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
