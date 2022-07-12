package model.product;

import io.IO;

import java.util.Scanner;

public class MovieDisc extends Product {
    private String producer; // Nhà sản xuất
    private String director; // Đạo diễn
    // Thể loại
    private String genre;
    // Năm xuất bản
    private String publishYear;
    // Diễn viên chính
    private String mainActor;

    public MovieDisc(Scanner scanner) {
        super(scanner);
        producer = IO.getString(scanner, "", "Nhà sản xuất: ");
        director = IO.getString(scanner, "", "Đạo diễn: ");
        genre = IO.getString(scanner, "", "Thể loại: ");
        publishYear = IO.getString(scanner, "", "Năm xuất bản: ");
        mainActor = IO.getString(scanner, "", "Diễn viên chính: ");
    }

    public MovieDisc(String name, int quantity, double sellPrice, double importPrice, String producer, String director, String genre, String publishYear, String mainActor) {
        super(name, quantity, sellPrice, importPrice);
        this.producer = producer;
        this.director = director;
        this.genre = genre;
        this.publishYear = publishYear;
        this.mainActor = mainActor;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %20s | %20s | %20s | %20s | %20s |", producer, director, genre, publishYear, mainActor);
    }
}
