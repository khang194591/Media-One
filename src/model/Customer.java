package model;

import model.person.Person;

import java.util.Scanner;

public class Customer extends Person {
    public int totalPurchasedTimes;
    public double totalMoneyPurchased;
    public Rank rank;

    public Customer(String name, int age) {
        super(name, age);
    }
    public Customer(Scanner scanner){
        super(scanner);
    }

    public int getTotalPurchasedTimes() {
        return totalPurchasedTimes;
    }

    public void setTotalPurchasedTimes(int totalPurchasedTimes) {
        this.totalPurchasedTimes = totalPurchasedTimes;
    }

    public double getTotalMoneyPurchased() {
        return totalMoneyPurchased;
    }

    public void setTotalMoneyPurchased(double totalMoneyPurchased) {
        this.totalMoneyPurchased = totalMoneyPurchased;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Customer: " + getName() + "\n";
    }
}
