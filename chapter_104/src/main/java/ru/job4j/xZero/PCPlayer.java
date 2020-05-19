package ru.job4j.xZero;

import java.util.*;

public class PCPlayer extends Player{

    private double deep;
    private double size;
    private String enemy;
    private HashMap<Integer[], Double> moves;
    private Integer[] cell;
    private int lim;

    public PCPlayer(String name, String symbol) {
        super(name, symbol);
    }

    @Override
    public boolean makeMove(Field field, InOut inOut) {
        moves = new HashMap<>();
        String fieldView = field.fieldView();
        this.size = Math.sqrt(fieldView.length());
        this.lim = 3;
        if (enemy == null || enemy.length() > 1) {
            whoIsEnemy(fieldView);
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
                Field temp = field.copy();
                if (temp.move(row, col, one)) {
                    if (deep == 1) {
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

    private void whoIsEnemy(String fieldView) {
        for (char letter: fieldView.toCharArray()) {
            if (letter != ' ' && !(letter == symbol.charAt(0))) {
                enemy = Character.toString(letter);
                break;
            }
        }
        if (enemy == null) {
            enemy = symbol + "E";
        }
    }
}