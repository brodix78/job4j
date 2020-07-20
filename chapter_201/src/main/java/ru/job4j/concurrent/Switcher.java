package ru.job4j.concurrent;

import java.util.concurrent.Semaphore;

public class Switcher {
    private static Semaphore semaphore = new Semaphore(1);
    private static volatile boolean aTurn = true; // (тут моя невнимательность - был уверен, что воткнул volatile)

    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
            () -> {
                while (true) {
                    while (!aTurn) {
                        // Для равномерного вывода и сомневающихся
                        // перекинул паузу на верх: "Thread A" всеравно выводится первой
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
                }
            });
        Thread second = new Thread(
            () -> {
                while (true) {
                    //Вторая ветка может проверить флаг первой, и получить разрешение от семафора,
                    while (aTurn) {
                        try {
                            semaphore.acquire();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        //но тут же вернет его если флаг true, поэтому и aTurn играет роль.
                        if (aTurn) {
                            semaphore.release();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Паузу поднял наверх для равномерного вывода
                    System.out.println("Thread B");
                    semaphore.release();
                    aTurn = true;
                }
            }
    );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
