package ru.job4j.exam;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {

    Player newOne = new Player("Newone");

    @Test
    public void whenMakeTwoMovesWithTwoStepsCellIsFour() {
        newOne.makeSteps(2);
        newOne.makeSteps(2);
        assertThat(newOne.getCell, is(4));
    }

    @Test
    public void whenAddTwoExtraMovesWhenTwoExtraMovesLeft() {
        newOne.addMoves(2);
        assertThat(newOne.movesLeft, is(2));
    }

    @Test
    public void whenAddTwoExtraMovesAndMakeMoveWhenOneExtraMovesLeft() {
        newOne.addMoves(2);
        newOne.makeSteps(2);
        assertThat(newOne.movesLeft, is(1));
    }

    @Test
    public void whenOneMoveLostNoMoveMade() {
        newOne.addMoves(-1);
        assertThat(newOne.makeSteps(2), is(false));
        assertThat(newOne.getCell, is(0));
    }

    @Test
    public void whenWonIsWin() {
        newOne.won;
        assertThat(newOne.isWin, is(true));
    }
}