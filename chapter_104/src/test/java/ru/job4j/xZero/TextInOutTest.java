package ru.job4j.xZero;

import org.junit.Test;

public class TextInOutTest {

    @Test
    public void fieldOutTest() {
        Field field = new Field(100);
        TextInOut tx = new TextInOut();
        tx.showField(field);
    }


    @Test
    public void gama(){
        Player Anya = new Player("Anya", "X");
        Player Senya = new Player("Senya", "O");
        Game game = new Game(Anya, Senya);
        game.game();
    }

}
