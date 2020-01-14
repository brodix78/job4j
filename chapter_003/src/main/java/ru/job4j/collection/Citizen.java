package ru.job4j.collection;

import java.util.Objects;

public class Citizen {
    private String passport;
    private String ctznName;

    public Citizen(String passpot, String ctznName) {
        this.passport = passpot;
        this.ctznName = ctznName;
    }

    public String getPassport() {
        return passport;
    }

    public String getCtznName() {
        return ctznName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Citizen citizen = (Citizen) o;
        return Objects.equals(this.passport, citizen.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.passport);
    }
}
