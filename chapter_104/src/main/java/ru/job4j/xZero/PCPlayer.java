package ru.job4j.xZero;

import java.util.*;

public class PCPlayer extends Player{

    int deep;
    int size;
    String enemy;
    HashMap<Integer[], Double> moves;
    Integer[] cell;
    int lim;

    public PCPlayer(String name, String symbol) {
        super(name, symbol);
    }

    @Override
    public boolean makeMove(Field field, InOut inOut) {
        moves = new HashMap<>();
        this.size = field.getSize();
        this.lim = 3;
        enemy = null;
        for (String[] cells : field.getCells()) {
            for (String cell : cells) {
                if (cell != null && !cell.equals(symbol)) {
                    enemy = cell;
                    break;
                }
            }
            if (enemy != null) {
                break;
            }
        }
        if (enemy == null) {
            enemy = symbol + "E";
        }
        deep = 0;
        serialThinking(field.copy(), symbol, enemy);
        Integer[] move = selectBest();
        inOut.output(String.format("My move is: %s %s", move[0] + 1, move[1] + 1));
        field.move(move[0], move[1], symbol);
        return field.isWin(symbol);
    }

    private void serialThinking(Field field, String one, String two) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                deep++;
                if (field.getCells()[row][col] == null) {
                    Field temp = field.copy();
                    temp.move(row, col, one);
                    if (deep==1) {
                        cell = new Integer[]{row, col};
                        moves.put(cell, 0.0);
                    }
                    if (deep == 2) {
                        System.out.println("SOS " + cell[0] + cell[1] + field.isWin(enemy) + "  " + one);
                    }
                    moves.put(cell, moves.get(cell) + (double) temp.canWin(symbol) / size / deep / deep);
                    if (field.isWin(symbol)) {
                        moves.put(cell, moves.get(cell) + (double) size * size / deep / deep);
                    } else if (field.isWin(enemy)) {
                        moves.put(cell, moves.get(cell) + (double) size / (deep - 1));
                        if (deep == 1) {
                            System.out.println("SOS " + cell[0] + cell[1]);
                        }
                    } else if (temp.freeCells() && deep < lim) {
                        serialThinking(temp, two, one);
                    }
                }
                deep--;
            }
        }
    }


    private Integer[] selectBest() {
        double bestK = 0;
        Integer[] bestCell = null;
        for (Integer[] cell : moves.keySet()) {
            System.out.println(String.format("%s %s - K = %s", cell[0] + 1, cell[1] + 1, moves.get(cell)));
        }
        for (Integer[] cell : moves.keySet()) {
            if(moves.get(cell) > bestK) {
                bestK = moves.get(cell);
                bestCell =cell;
            }
        }
        return bestCell;
    }
}