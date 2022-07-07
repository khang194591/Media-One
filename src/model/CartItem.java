package model;

import model.product.Product;

public class CartItem {
    private Product product;
    private int quantity;        // Luon phai >= 0
    private double unitPrice;    // Luon phai >= 0
    private double allPrice;    // Luon phai >= 0 - khong co phuong thuc set

    public CartItem(Product product, int quantity, double unitPrice) {
        this.product = product;
        this.quantity = Math.max(quantity, 0);
        this.unitPrice = (unitPrice > 0) ? unitPrice : 0;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product p) {
        product = p;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantiy(int quan) {
        quantity = Math.max(quan, 0);
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double price) {
        if (price < 0) unitPrice = 0;
        else unitPrice = price;
    }

    // Khong co setAllPrice
    public double getAllPrice() {
        return unitPrice * quantity;
    }

    public void showInfor() {
        //product.showInfor();
        System.out.println("CartItem: " + quantity + ", " + unitPrice);
    }
}
