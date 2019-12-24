package ru.job4j.oop;

import java.lang.reflect.AnnotatedElement;

public class Cat {

    private String name;

    public void giveNick(String nick) {
        this.name = nick;
    }

    private String food;

    public void show() {
        System.out.println("There are " + this.name + "'s food.");
        System.out.println(this.food);
    }

    public void eat(String meat) {
        this.food = meat;
    }

    public static void main(String[] args) {
        Cat gav = new Cat(), black = new Cat();
        gav.giveNick("Gav");
        gav.eat("kotleta");
        gav.show();
        black.giveNick("Black");
        black.eat("fish");
        black.show();
    }
}