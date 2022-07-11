package model.product;

import java.util.Scanner;

public class Book extends Product {
    // Nhà sản xuất
    private String publisher;
    // Tác giả
    private String author;
    // Mô tả
    private String description;
    // Năm xuất bản
    private int publishYear;
    // Tái bản lần mấy
    private String republish;

    public Book(Scanner scanner) {
        super(scanner);
        System.out.print("Nhà xuất bản: ");
        publisher = scanner.nextLine().trim();
        System.out.print("Tác giả: ");
        author = scanner.nextLine().trim();
        System.out.print("Mô tả: ");
        description = scanner.nextLine().trim();
        System.out.print("Năm xuất bản: ");
        publishYear = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Tái bản: ");
        republish = scanner.nextLine().trim();
    }

    public Book(String name, int quantity, double sellPrice, double importPrice, String publisher, String author, String description, int publishYear, String republish) {
        super(name, quantity, sellPrice, importPrice);
        this.publisher = publisher;
        this.author = author;
        this.description = description;
        this.publishYear = publishYear;
        this.republish = republish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getRepublish() {
        return republish;
    }

    public void setRepublish(String republish) {
        this.republish = republish;
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
        return super.toString() + String.format(" %20s | %20s | %20s | %20d | %20s |", publisher, author, description, publishYear, republish);
    }
}
