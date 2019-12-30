package ru.job4j.io;

import java.util.Random;
import java.util.Scanner;

public class MagicBall {

    public static void main(String[] args) {
        MagicBall magic = new MagicBall();
        magic.question("Я великий Оракул. Что ты хочешь узнать? ");
        String question;
        do {
            System.out.println(magic.answer());
            question = magic.question("Задай мне еще вопрос или скажи \"Пока\" если твое любопытство удовлетворено. ");
        } while (!question.equals("Пока"));
    }

    private String question(String ballSaid) {
        Scanner input = new Scanner(System.in);
        System.out.print(ballSaid);
        String question = input.nextLine();
        return question;
    }

    public String answer() {
        int rand = new Random().nextInt(3);
        String answer = "Может быть";
        switch (rand) {
            case 0: {
                answer = "Да";
                break;
            }
            case 1: {
                answer = "Нет";
                break;
            }
            default: break;
        }
        return answer;
    }
}
