package ru.job4j.xZero;

import java.util.Objects;

public class Player {
    protected final String name;
    protected final String symbol;

    public String getName() {
        return (this.name == null
                || this.name.replaceAll(" ", "").equals("")) ? "No name" : name;
    }

    public Player(String name, String symbol) {
        this.name =  name;
        this.symbol = symbol.length() > 1 ? symbol.substring(0, 1) : symbol;
    }

    public boolean makeMove(Field field, InOut inOut) {
        return inOut.makeMove(field, this.name, this.symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(symbol, player.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, symbol);
    }
}