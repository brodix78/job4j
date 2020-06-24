package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    private void emailTo(User user) {
        pool.submit(() -> send(user.getName(),
                String.format("Add a new event to %s", user.getName()),
                user.getEmail()));
    }

    private void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String suject, String body, String email) {
    }
}
