package ru.job4j.xZero;

public class Field {

    private final int size;
    private final String[][] cells;
    private int filled;

    public Field(int size) {
        this.size = size;
        this.cells =  new String[size][size];
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

    public String fieldView() {
        StringBuilder view = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (cells[row][col] != null) {
                    view.append(cells[row][col]);
                } else {
                    view.append(" ");
                }
            }
        }
        return view.toString();
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