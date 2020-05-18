package ru.job4j.xZero;

import java.util.*;

public class PCPlayer extends Player{

    int deep;
    int size;
    String enemy;
    ArrayList<ArrayList<Integer[]>> winMoves;
    ArrayList<ArrayList<Integer[]>> lostMoves;
    ArrayList<Integer[]> anyMoves;
    int lim;

    public PCPlayer(String name, String symbol) {
        super(name, symbol);
    }

    @Override
    public boolean makeMove(Field field, InOut inOut) {
        winMoves = new ArrayList<>();
        lostMoves = new ArrayList<>();
        anyMoves = new ArrayList<>();
        this.size = field.getSize();
        this.lim = this.size * 2;
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
        serialThinking(field.copy(), symbol, enemy, new ArrayList<>());
        Integer[] move = selectBest();
        inOut.output(String.format("My move is: %s %s", move[0] + 1, move[1] + 1));
        field.move(move[0], move[1], symbol);
        return field.isWin(symbol);
    }

    private void serialThinking(Field field, String one, String two, ArrayList<Integer[]> prevMoves) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (field.getCells()[row][col] == null) {
                    Field temp = field.copy();
                    temp.move(row, col, one);
                    ArrayList<Integer[]> moves = new ArrayList<>(prevMoves);
                    moves.add(new Integer[] {row, col});
                    if (field.isWin(symbol)) {
                        if (moves.size() < lim) {
                            lim = moves.size();
                        }
                        winMoves.add(moves);
                        System.out.println("win  " + lim);
                    } else if (field.isWin(enemy)) {
                        if (moves.size() < lim) {
                            lim = moves.size();
                        }
                        lostMoves.add(moves);
                        System.out.println("lose  " + lim);
                    } else if (temp.freeCells()
                            && temp.canWin(symbol) && moves.size() < lim) {
                        serialThinking(temp, two, one, moves);
                    } else if (!temp.freeCells()) {
                        anyMoves.add(moves.get(0));
                    }
                }
            }
        }
    }


    private Integer[] selectBest() {
        Integer[] best = null;
        System.out.println(winMoves.size());
        System.out.println(lostMoves.size());
        System.out.println(anyMoves.size());
        HashMap<Integer[], Integer> winRsl = new HashMap<>();
        LinkedList<ArrayList<Integer[]>> wM= new LinkedList<>();
        winMoves.stream().sorted((f, s) -> f.size() - s.size()).forEach(wM::add);
        LinkedList<ArrayList<Integer[]>> lM= new LinkedList<>();
        lostMoves.stream().sorted((f, s) -> f.size() - s.size()).forEach(lM::add);
        if (wM.size() > 0) {
            int bestSize = wM.getFirst().size();
            for (ArrayList<Integer[]> moves : wM) {
                if (moves.size() > bestSize) {
                    break;
                }
                if (winRsl.containsKey(moves.get(0))) {
                    winRsl.put(moves.get(0), winRsl.get(moves.get(0)) + 1);
                } else {
                    winRsl.put(moves.get(0), 1);
                }
            }
            bestSize = lM.getFirst().size();
            for (ArrayList<Integer[]> moves : lM) {
                if (moves.size() > bestSize) {
                    break;
                }
                if (winRsl.containsKey(moves.get(0))) {
                    winRsl.remove(moves.get(0));
                }
            }
            for (Integer[] key : winRsl.keySet()) {
                if (best == null) {
                    best = key;
                } else if (winRsl.get(key) > winRsl.get(best)) {
                    best = key;
                }
            }
        }
        if (best == null) {
            best = anyMoves.size() > 0 ? anyMoves.get(0) : lostMoves.get(0).get(0);
        }
        return best;
    }
}