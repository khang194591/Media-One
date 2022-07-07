package model.product;

import java.util.Scanner;

public class Book extends Product {
    // Nhà sản xuất
    private String publisher;
    // Tác giả
    private String author;

    public Book(Scanner scanner) {
        super(scanner);
        System.out.print("Nhà xuất bản: ");
        publisher = scanner.nextLine().trim();
        System.out.print("Tác giả: ");
        author = scanner.nextLine().trim();
    }

    public Book(String name, int quantity, double sellPrice, double importPrice, String publisher, String author) {
        super(name, quantity, sellPrice, importPrice);
        this.publisher = publisher;
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %20s | %20s |", publisher, author);
    }
}
