package ru.job4j.nba;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class Base {
    private final int id;
    AtomicReference<Integer> version = new AtomicReference<>(0);

    public Base(int id) {
        this.id = id;
    }

    public Base updateVersion(Base base) {
        if (version.compareAndSet(base.getVersion(), version.get() + 1)) {
            base.incrementVersion();
            return this;
        } else {
            throw new OptimisticException("Versions collision");
        }
    }

    public void incrementVersion() {
        version.set(version.get() + 1);
    }

    public int getVersion() {
        return version.get();
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return id == base.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
