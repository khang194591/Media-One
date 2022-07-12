package model.product;

import io.IO;

import java.util.Scanner;

public class MusicDisc extends Product {
    private String producer; // Nhà sản xuất
    private String singer; // Tên ca sĩ
    // Thời lượng
    private String duration;
    // Thể loại
    private String genre;

    public MusicDisc(Scanner scanner) {
        super(scanner);
        System.out.print("Nhà sản xuất: ");
        producer = IO.getString(scanner, "", "Nhà sản xuất: ");
        singer = IO.getString(scanner, "", "Ca sĩ : ");
        duration = IO.getString(scanner, "", "Thời lượng: ");
        genre = IO.getString(scanner, "", "Thể loại : ");
    }

    public MusicDisc(String name, int quantity, double sellPrice, double importPrice, String producer, String singer) {
        super(name, quantity, sellPrice, importPrice);
        this.producer = producer;
        this.singer = singer;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %20s | %20s | %20s | %20s |", producer, singer, duration, genre);
    }
}
