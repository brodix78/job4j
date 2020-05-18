package ru.job4j.xZero;

import java.util.*;

public class PCPlayer extends Player{

    double deep;
    double size;
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
                    if (one.equals(symbol)) {
                        if(temp.isWin(symbol)) {
                            moves.put(cell, moves.get(cell) + size * size * 1.1 / (deep) );
                        } else {
                            moves.put(cell, moves.get(cell) + temp.canWin(symbol) / (size * deep));
                            if (temp.freeCells() && deep < lim) {
                                serialThinking(temp, enemy, symbol);
                            }
                        }
                    } else if (one.equals(enemy)) {
                        if (temp.isWin(enemy)) {
                            moves.put(cell, moves.get(cell) - size * size * 0.9 / (deep - 1));
                        } else {
                            if (temp.freeCells() && deep < lim) {
                                serialThinking(temp, symbol, enemy);
                            }
                        }
                    }
                }
                deep--;
            }
        }
    }


    private Integer[] selectBest() {
        Double bestK = null;
        Integer[] bestCell = null;
        for (Integer[] cell : moves.keySet()) {
            if(bestK == null || moves.get(cell) > bestK) {
                bestK = moves.get(cell);
                bestCell =cell;
            }
        }
        return bestCell;
    }
}