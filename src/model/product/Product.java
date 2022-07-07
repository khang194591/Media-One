package model.product;

import io.IO;

import java.io.Serializable;
import java.util.Scanner;
import java.util.UUID;

public class Product implements Serializable, IO {
    // Mã sản phẩm
    protected String code;
    // Tên sản phẩm
    protected String name;
    // Số lượng sản phẩm
    protected int quantity;
    // Giá bán ra
    protected double sellPrice;
    // Giá nhập vào
    protected double importPrice;

    public Product(Scanner scanner) {
        code = UUID.randomUUID().toString();
        System.out.print("Tên sản phẩm: ");
        name = scanner.nextLine().trim();
        System.out.print("Số lượng: ");
        quantity = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Giá nhập: ");
        importPrice = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Giá bán: ");
        sellPrice = Double.parseDouble(scanner.nextLine().trim());
    }

    public Product(String name, int quantity, double sellPrice, double importPrice) {
        code = UUID.randomUUID().toString();
        this.name = name;
        this.quantity = quantity;
        this.sellPrice = sellPrice;
        this.importPrice = importPrice;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    @Override
    public String toString() {
        return String.format(" %40s | %40s | %10d | %10.2f | %10.2f |", code, name, quantity, importPrice, sellPrice);
    }
}
