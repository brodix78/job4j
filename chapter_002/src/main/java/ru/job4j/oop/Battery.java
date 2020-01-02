package ru.job4j.oop;

public class Battery {
    private int load;

    private String brand;

    public Battery(int charged, String brand) {
        this.load = charged;
        this.brand = brand;
    }

    public void exchange(Battery another) {
        another.load = another.load + this.load;
        this.load = 0;
    }

    public void showBatSt() {
        System.out.println(this.brand + " charged " + this.load + "%");
    }

    public static void main(String[] args) {
        Battery maxwell = new Battery(50, "Maxwell"), gp = new Battery(20, "GP");
        System.out.println("Before:");
        maxwell.showBatSt();
        gp.showBatSt();
        maxwell.exchange(gp);
        System.out.println("After:");
        maxwell.showBatSt();
        gp.showBatSt();
    }
}