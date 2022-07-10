package model;

import java.util.Scanner;

public class MovieDisc extends Product {
    private String producer; // Nhà sản xuất
    private String director; // Đạo diễn

    public MovieDisc(Scanner scanner) {
        super(scanner);
        System.out.print("Producer: ");
        producer = scanner.nextLine().trim();
        System.out.print("Director: ");
        director = scanner.nextLine().trim();
    }

    public MovieDisc(String name, int quantity, double sellPrice, double importPrice, String producer, String director) {
        super(name, quantity, sellPrice, importPrice);
        this.producer = producer;
        this.director = director;
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
        return super.toString()+ String.format(" %20s | %20s |", producer, director);
    }
}
