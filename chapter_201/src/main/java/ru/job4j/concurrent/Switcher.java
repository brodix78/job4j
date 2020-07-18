package ru.job4j.concurrent;

import java.util.concurrent.Semaphore;

public class Switcher {
    private static Semaphore semaphore = new Semaphore(1);
    private static boolean aTurn = true;

    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
            () -> {
                while (true) {
                    while (!aTurn) {
                        try {
                            semaphore.acquire();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        if (!aTurn) {
                            semaphore.release();
                        }
                    }
                    System.out.println("Thread A");
                    aTurn = false;
                    semaphore.release();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        Thread second = new Thread(
            () -> {
                while (true) {
                    while (aTurn) {
                        try {
                            semaphore.acquire();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        if (aTurn) {
                            semaphore.release();
                        }
                    }
                    System.out.println("Thread B");
                    semaphore.release();
                    aTurn = true;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
    );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
