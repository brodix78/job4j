package ru.job4j.pojo;

public class Library {

    public static void main(String[] args) {
        Book[] books = new Book[4];
        books[0] = new Book("Sweet dreams", 200);
        books[1] = new Book("Fairy star", 250);
        books[2] = new Book("Dirty desires", 300);
        books[3] = new Book("Clean code", 125);
        Library lib = new Library();
        lib.printLib(books);
        System.out.println();
        Book book = books[0];
        books[0] = books[3];
        books[3] = book;
        lib.printLib(books);
        System.out.println();
        lib.printLib(books, "Clean code");
    }

    public void printLib(Book[] books) {
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            System.out.println("Книга " + (i + 1) +  " \"" + book.getName() + "\", количество страниц: " + book.getPages());
        }
    }

    public void printLib(Book[] books, String name) {
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            if (book.getName().equals(name)) {
                System.out.println("Книга " + (i + 1) +  " \"" + book.getName() + "\", количество страниц: " + book.getPages());
            }
        }
    }
}