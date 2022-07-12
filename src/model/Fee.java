package model;

import io.IO;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Fee implements Serializable {
    private String title; // chứa kiểu ?
    private String description;
    private double totalMoney;

    private Date date;

    public Fee(Scanner scanner) {
        title = IO.getString(scanner, "", "Kiểu phí: ");
        totalMoney = Double.parseDouble(IO.getString(scanner, "", "Tổng tiền: "));
        description = IO.getString(scanner, "", "Nội dung miêu tả (thông tin phí, số lượng): ");
        date = new Date();
        System.out.print("Ngày tháng năm: " + date);
    }

    public Fee(String title, String description, double totalMoney, Date date) {
        this.title = title;
        this.description = description;
        this.totalMoney = totalMoney;
        this.date = date;
    }

    public void show() {
        System.out.println("Kiểu phí: " + title);
        System.out.println("Tổng tiền: " + totalMoney);
        System.out.println("Nội dung miêu tả (thông tin phí, số lượng): " + description);
        System.out.println("Ngày tháng năm: " + date);
    }

    public int getMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public int getYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Fee{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", totalMoney=" + totalMoney +
                ", date=" + date +
                '}';
    }
}
