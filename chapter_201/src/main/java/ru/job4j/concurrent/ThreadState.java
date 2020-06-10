package ru.job4j.concurrent;

public class ThreadState {

    public static void main(String[] args) {
        Thread first = new Thread(() -> System.out.println("first"));
        Thread second = new Thread(() -> System.out.println("second"));
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                || second.getState() != Thread.State.TERMINATED) {
            System.out.println(String.format("First thread state: %s%nSecond thread state: %s",
                    first.getState(), second.getState()));
        }
        System.out.println("Threads are terminated");
    }
}
