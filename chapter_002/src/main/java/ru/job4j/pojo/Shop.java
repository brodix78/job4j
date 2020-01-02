package ru.job4j.pojo;

public class Shop {

    public static void main(String[] args) {
        Product[] products = new Product[5];
        products[0] = new Product("Milk", 10);
        products[1] = new Product("Bread", 4);
        products[2] = new Product("Egg", 19);
        Shop shop = new Shop();
        shop.printStock(products);
        System.out.println();
        System.out.println("Удаляем значение из ячейки с индексом 1");
        shop.delProd(products, 1);
        shop.printStock(products);
    }

    public void printStock(Product[] products) {
        for (int i = 0; i < products.length; i++) {
            Product product = products[i];
            if (product != null) {
                System.out.println(product.getName());
            } else {
                System.out.println("null");
            }
        }
    }

    public Product[] delProd(Product[] products, int index) {
        if (index < products.length) {
            products[index] = null;
            Shop shop = new Shop();
            shop.moveToL(products, index);
        }
        return products;
    }

    public void moveToL(Product[] products, int index) {
        for (int i = index; i < products.length - 1; i++) {
            products[i] = products[i + 1];
        }
        products[products.length - 1] = null;
    }
}