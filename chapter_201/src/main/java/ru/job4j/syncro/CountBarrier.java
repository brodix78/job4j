package ru.job4j.syncro;

public class CountBarrier {

    private final Object monitor = this;
    private final int total;
    private int count;

    public CountBarrier(int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count != total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountBarrier barrier = new CountBarrier(2);
        Thread masterOne = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.count();
                },
                "Master1"
        );
        Thread masterTwo = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.count();
                },
                "Master2"
        );
        Thread slave = new Thread(
                () -> {
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        slave.start();
        masterOne.start();
        masterTwo.start();
    }
}
