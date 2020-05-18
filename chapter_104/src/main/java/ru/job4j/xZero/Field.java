package ru.job4j.xZero;

import java.util.ArrayList;

public class Field {

    int size;
    String[][] cells;
    int filled;

    public Field() {
        this.size = 3;
        this.cells =  new String[3][3];
    }

    public Field(int size) {
        this.size = size;
        this.cells =  new String[size][size];
    }

    public int getSize() {
        return size;
    }

    public String[][] getCells() {
        return cells;
    }

    public void clearField() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++)
                this.cells[r][c] = null;
        }
    }

    public boolean move(int row, int column, String symbol) {
        boolean rsl = false;
        if (row >= 0 && row < size && column >= 0 && column < size
                && cells[row][column] == null) {
            cells[row][column] = symbol;
            filled++;
            rsl = true;
        }
        return rsl;
    }

    public boolean freeCells() {
        return filled < size * size;
    }

    public int freeCellsQuan() {
        return size * size - filled;
    }

    public boolean isWin(String symbol) {
        boolean win = false;
        int lX = 0;
        int rX = 0;
        for (int i = 0; i < size; i++) {
            if (cells[i][i] != null
                    && cells[i][i].equals(symbol)) {
                lX++;
                for (int n = 0; n < size; n++) {
                    win = true;
                    if (cells[i][n] == null || !cells[i][n].equals(symbol)) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    break;
                }
                for (int n = 0; n < size; n++) {
                    win = true;
                    if (cells[n][i] == null || !cells[n][i].equals(symbol)) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    break;
                }
            }
            if (cells[i][size - i - 1] != null
                    && cells[i][size - i - 1].equals(symbol)) {
                rX++;
            }
        }
        if (lX == size || rX == size) {
            win = true;
        }
        return win;
    }

    public int canWin(String symbol) {
        int win = 0;
        int lX = 0;
        int rX = 0;
        for (int i = 0; i < size; i++) {
            if (cells[i][i] == null
                    || cells[i][i].equals(symbol)) {
                lX++;
                boolean can = true;
                for (int n = 0; n < size; n++) {
                    if (cells[i][n] != null &&!cells[i][n].equals(symbol)) {
                        can = false;
                        break;
                    }
                }
                if(can) {
                    win++;
                }
                can = true;
                for (int n = 0; n < size; n++) {
                    if (cells[n][i] != null && !cells[n][i].equals(symbol)) {
                        can = false;
                        break;
                    }
                }
                if(can) {
                    win++;
                }
            }
            if (cells[i][size - i - 1] == null
                    || cells[i][size - i - 1].equals(symbol)) {
                rX++;
            }
        }
        if (rX == size) {
            win++;
        }
        if (lX == size) {
            win++;
        }
        return win;
    }


    public Field copy() {
        Field copy = new Field(this.size);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++)
                copy.cells[r][c] = this.cells[r][c];
        }
        return copy;
    }
}