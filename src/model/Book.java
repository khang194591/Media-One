package model;

import java.util.Scanner;

public class Book extends Product {
    private String publisher;   // Nhà sản xuất
    private String author;  // Tác giả
    private String overview; // giới thiệu tổng quát

    private int releaseYear; //  năm phát hành
    private int timeRepublish; // năm tái bản

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getTimeRepublish() {
        return timeRepublish;
    }

    public void setTimeRepublish(int timeRepublish) {
        this.timeRepublish = timeRepublish;
    }

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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String newOverview) {
        this.overview = newOverview;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %20s | %20s |", publisher, author);
    }
}
