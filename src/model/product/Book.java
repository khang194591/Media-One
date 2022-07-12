package model.product;

import io.IO;

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
        publisher = IO.getString(scanner, "", "Nhà xuất bản: ");
        author = IO.getString(scanner, "", "Tác giả: ");
        description = IO.getString(scanner, "", "Mô tả: ");
        publishYear = Integer.parseInt(IO.getString(scanner, "", "Năm xuất bản: "));
        republish = IO.getString(scanner, "", "Tái bản: ");
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
