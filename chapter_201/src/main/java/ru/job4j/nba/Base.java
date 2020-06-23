package ru.job4j.nba;

import java.util.Objects;

public class Base {
    private final int id;
    private final int version;

    public Base(int id) {
        this.id = id;
        this.version = 0;
    }

    private Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public Base updateVersion() {
        return new Base(this.id, this.version + 1);
    }

    public int getVersion() {
        return version;
    }

    public int getId() {
        return id;
    }

    public static Base of(Base base) {
        return base != null ? new Base(base.getId(), base.getVersion()) : null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return id == base.id &&
                version == base.version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }
}
