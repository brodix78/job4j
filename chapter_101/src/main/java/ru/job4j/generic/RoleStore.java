package ru.job4j.generic;

public class RoleStore<Role> implements Store {

    public RoleStore(int size) {
        func.newStore(size);
    }

    public class Func extends AbstractStore<Role> {

    }

    Func func = new Func();

    @Override
    public void add(Base model) {
        func.add(model);
    }

    @Override
    public boolean replace(String id, Base model) {
        return func.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return func.delete(id);
    }

    @Override
    public Base findById(String id) {
        return func.findById(id);
    }
}
