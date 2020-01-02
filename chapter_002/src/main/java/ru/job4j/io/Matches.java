package ru.job4j.io;

import java.util.Scanner;

public class Matches {

    public static void main(String[] args) {
        int player = 2;
        int matches = 11;
        Matches game = new Matches();
        do {
            player = (player == 2) ? 1 : 2;
            matches = matches - game.takeMatch(matches, player);
        } while (matches > 0);
        System.out.println("Игра окончена. Победил игрок " + player);
    }

    public int takeMatch(int matches, int player) {
        System.out.print("Осталось " + matches + " спичек. Игрок " + player + " забирает спички - ");
        int takeMatch;
        Scanner input = new Scanner(System.in);
        do {
            takeMatch = Integer.valueOf(input.nextLine());
        } while (takeMatch > 3 || takeMatch < 1 || takeMatch > matches);
        return takeMatch;
    }
}
