package ru.job4j.concurrent;

public class MasterSlaveBarrier {

    private volatile boolean master = true;

    public synchronized void tryMaster() {
        while (!master) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void trySlave() {
        while (master) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void doneMaster() {
        master = false;
        notifyAll();
    }

    public synchronized void doneSlave() {
        master = true;
        notifyAll();
    }
}
