package ru.job4j.xZero;

public class Game {

    private InOut inOut;
    private int fieldSize;
    private Player one;
    private Player two;
    private int rounds;

    public Game(Player one, Player two) {
        this.one = one;
        this.two = two;
        this.inOut = new TextInOut();
        this.fieldSize = 3;
        this.rounds = 1;
    }

    public Game(Player one, Player two, int size, int rounds) {
        this.one = one;
        this.two = two;
        this.inOut = new TextInOut();
        this.fieldSize = size;
        this.rounds = rounds;
    }

    public Player game() {
        boolean round = true;
        do {
            Player winner = round ? oneRound(one, two) : oneRound(two, one);
            if (winner == null) {
                inOut.output("We have no winner in the game, but we need him :)");
                inOut.output("");
            }
            round = !round;
        } while (one.getWon() < rounds && two.getWon() < rounds);
        Player winner = one.getWon() > two.getWon() ? one : two;
        inOut.output(String.format("The winner is %s", winner.name));
        return winner;
    }

    public Player oneRound(Player first, Player second) {
        Player current = second;
        boolean win;
        Field field = new Field(fieldSize);
        do {
            inOut.showField(field);
            current = first.equals(current) ? second : first;
            win = current.makeMove(field, inOut);
        } while(!win && field.freeCells());
        if (win) {
            current.won();
        }
        inOut.showField(field);
        return win ? current : null;
    }

    public static void main(String[] args) {
        Player Anya = new Player("Anya", "X");
        Player Senya = new PCPlayer("Senya", "O");
        Game game = new Game(Anya, Senya, 3, 1);
        game.game();
    }
}