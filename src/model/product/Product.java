package model.product;

import io.IO;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class Product implements Serializable {
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
        name = IO.getString(scanner, "", "Tên sản phẩm: ");
        quantity = Integer.parseInt(IO.getString(scanner, "", "Số lượng: "));
        importPrice = Double.parseDouble(IO.getString(scanner, "", "Giá nhập: "));
        sellPrice = Double.parseDouble(IO.getString(scanner, "", "Giá bán: "));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity && Double.compare(product.sellPrice, sellPrice) == 0 && Double.compare(product.importPrice, importPrice) == 0 && code.equals(product.code) && name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, quantity, sellPrice, importPrice);
    }

    public boolean equalsName(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }
}
