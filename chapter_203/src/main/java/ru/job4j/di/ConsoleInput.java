package ru.job4j.di;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput {

    public String getString(String req) {
        Scanner sc = new Scanner(System.in);
        System.out.println(req);
        return  sc.nextLine();
    }

    public int getInt(String req) {
        Integer rsl = null;
        while (rsl == null) {
            try {
                rsl = Integer.parseInt(getString(req));
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число");
            }
        }
        return rsl;
    }
}
