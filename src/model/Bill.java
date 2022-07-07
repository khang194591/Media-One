package model;

import model.person.Customer;
import model.person.Staff;

import java.util.*;


public class Bill {
    private final List<CartItem> listCart;
    private String code;
    private int qtyOrdered;
    private Date dateBuy;
    private Customer customer;
    private Staff staff;
    private double discount;   // giam gia neu khong co = 0
    private double totalMoney; // tong tien

    public Bill(Staff staff, Customer customer) {
        // random code
        code = UUID.randomUUID().toString();
        dateBuy = new Date();
        listCart = new ArrayList<>();
        discount = 0; // default

        this.staff = staff;
        this.customer = customer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String s) {
        code = s;
    }

    public int getQtyOrdered() {
        return qtyOrdered;
    }

    public void setQtyOrdered(int qty) {
        qtyOrdered = Math.max(qty, 0);
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date date) {
        dateBuy = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer otherCus) {
        customer = otherCus;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff otherStaff) {
        staff = otherStaff;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscout(double newDiscount) {
        if (newDiscount < 0) discount = 0;
        else discount = newDiscount;
    }

    // add
    public boolean addCartItem(CartItem item) {
        qtyOrdered += item.getQuantity();
        return listCart.add(item);

    }

    //remove
    public boolean removeCartItem(CartItem item) {
        if (listCart.removeIf(n -> n == item)) {
            qtyOrdered -= item.getQuantity();
            return true;
        }
        return false;
    }

    // tinh tong
    public double totalCost() {
        double total = 0;
        for (CartItem cartItem : listCart) {
            total += cartItem.getAllPrice();
        }
        return total;
    }
    // Can kiem tra Class nay

    public void showInfor() {
        Iterator<CartItem> i = listCart.iterator();
        //System.out.println("Bill Inforamtion:" );
        System.out.println("Bill code: " + code);
        while (i.hasNext()) {
            CartItem cart = (CartItem) i.next();
            cart.showInfor();
        }
    }
}
