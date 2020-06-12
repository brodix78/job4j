package ru.job4j.concurrent;

import java.util.LinkedList;
import java.util.List;

public class ConsoleProgress implements Runnable{

    @Override
    public void run() {
        LinkedList<Character> chars = new LinkedList<>(List.of('-', '\\', '|', '/'));

        while (!Thread.currentThread().isInterrupted()) {
            System.out.print(String.format("\r load %s", chars.getFirst()));
            chars.add(chars.pollFirst());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        progress.interrupt();
    }
}
