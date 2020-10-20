package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.di");
        context.refresh();
        StartUI ui = context.getBean(StartUI.class);
        ui.add("First subject");
        ui.add("Second subject");
        ui.print();
        ui = context.getBean(StartUI.class);
        ui.add("Third subject");
        ui.add("Fourth subject");
        ui.print();
    }
}
