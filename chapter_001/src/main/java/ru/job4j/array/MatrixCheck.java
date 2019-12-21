package ru.job4j.array;

public class MatrixCheck {
    public static boolean monoHorizontal(char[][] board, int index) {
        boolean result = true;
        for (int i = 0; i < board.length; i++) {
            if (board[index][i] != 'X') {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean monoVertical(char[][] board, int index) {
        boolean result = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][index] != 'X') {
                result = false;
                break;
            }
        }
        return result;
    }

    public static int diagonalSearch(char[][] board) {
        int index = -1;
        for (int x = 0; x < board.length; x++) {
            if (board[x][x] == 'X') {
                index = x;
            }
        }
            return index;
    }

    public static boolean isWin(char[][] board) {
        boolean result = false;
        int index = diagonalSearch(board);
        if (index > -1) {
            if (monoHorizontal(board, index) || monoVertical(board, index)) {
                result = true;
            }
        }
        return result;
    }
}
