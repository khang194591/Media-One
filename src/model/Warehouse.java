package model;

import model.product.Book;
import model.product.MovieDisc;
import model.product.MusicDisc;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private final Store bookStore;
    private final Store musicDiscStore;
    private final Store movieDiscStore;
    private List<Bill> listBill; // chua cac bill da thanh toan
    private double profit; // Loi nhuan
    private int soldNumber;

    public Warehouse() {
        bookStore = new Store(Book.class);
        musicDiscStore = new Store(MusicDisc.class);
        movieDiscStore = new Store(MovieDisc.class);
        listBill = new ArrayList<>();
    }

    // add


    // thao tac voi Bill
    public void addListBill(Bill bill) {
        if (bill == null) return;
        listBill.add(bill);
    }

    public List<Bill> getListBill() {
        return listBill;
    }

    public void showAllBill() {
        for (Bill bill : listBill) {
            showBill(bill);
        }
    }

    public Store getBookStore() {
        return bookStore;
    }

    public Store getMusicDiscStore() {
        return musicDiscStore;
    }

    public Store getMovieDiscStore() {
        return movieDiscStore;
    }

    // Phuong thuc nay hoi ngu
    public void showBill(Bill bill) {
        if (listBill.contains(bill)) bill.showInfor();
    }
}//end class
