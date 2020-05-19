package ru.job4j.xZero;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TextInOut implements InOut {

    private PrintWriter writer;
    private BufferedReader reader;

    public TextInOut() {
        this.writer = new PrintWriter(System.out, true);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public TextInOut(PrintWriter writer, BufferedReader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public void showField(Field field) {
        StringBuilder table = new StringBuilder();
        String fieldView = field.fieldView();
        int size = (int) Math.sqrt(fieldView.length());
        String line = horLine(size);
        table.append(line);
        int index = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                table.append(String.format("| %s ", fieldView.charAt(index++)));
            }
            table.append(String.format("|%n"));
            table.append(line);
        }
        writer.println(table.toString());
    }

    @Override
    public boolean makeMove(Field field, String name, String symbol) {
        int row;
        int col;
        do {
            row = -1;
            col = -1;
            String move = input(String.format("%s, make your move (row column) : ", name));
            ArrayList<Integer> digits = new ArrayList<>();
            try (Scanner s = new Scanner(move)) {
                while (s.hasNextInt()) {
                    digits.add(s.nextInt());
                }
            }
            if (digits.size() > 1) {
                row = digits.get(0) - 1;
                col = digits.get(1) - 1;
            }
        } while (col == -1 || !field.move(row, col, symbol));
        return field.isWin(symbol);
    }

    @Override
    public String input(String query) {
        writer.println(query);
        return readLine();
    }

    @Override
    public void output(String text) {
        writer.println(text);
    }

    private String horLine(int cells) {
        StringBuilder line = new StringBuilder();
        int len = cells * 4 + 1;
        line.append("-".repeat(Math.max(0, len)));
        line.append(System.lineSeparator());
        return line.toString();
    }

    private String readLine() {
        String rsl = null;
        try {
            rsl = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}