package ru.job4j.array;

public class MatrixCheck {
    public static boolean monoHorizontal(char[][] board, char index) {
        boolean result = false;
        for (int x = 0; x < board.length; x++) {
            if (board[x][0] == index) {
                result = true;
                for (int y = 0; y < board[x].length; y++) {
                    if (board[x][y] != index) {
                        result = false;
                        break;
                    }
                }
            }
            if (result == true) {
                break;
            }
        }
        return result;
    }

    public static boolean monoVertical(char[][] board, char index) {
        boolean result = false;
        for (int y = 0; y < board[0].length; y++) {
            if (board[0][y] == index) {
                result = true;
                for (int x = 0; x < board.length; x++) {
                    if (board[x][y] != index) {
                        result = false;
                        break;
                    }
                }
            }
            if (result == true) {
                break;
            }
        }
        return result;
    }

    public static boolean monoDiagonal(char[][] board, char index) {
        boolean resultLeft = true;
        boolean resultRight = true;
        for (int x = 0; x < board.length; x++) {
            if (board[x][x] != index) {
                resultLeft = false;
            }
            if (board[x][board.length - x - 1] != index) {
                resultRight = false;
            }
        }
        if (resultLeft == false) {
            resultLeft = resultRight;
        }
            return resultLeft;
    }

    public static boolean isWin(char[][] board) {
        boolean result = false;
        if (monoHorizontal(board, 'X') || monoVertical(board, 'X') ||monoDiagonal(board, 'X')) {
            result = true;
        }
        return result;
    }
}
