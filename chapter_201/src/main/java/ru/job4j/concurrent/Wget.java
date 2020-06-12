package ru.job4j.concurrent;

public class Wget {

    public static void main(String[] args) {
        Thread percentShow = new Thread(() -> {
            System.out.print("Loading : 0%");
            for (int i = 1; i < 101; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.print(String.format("\rLoading : %s%%", i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        percentShow.start();
    }
}
