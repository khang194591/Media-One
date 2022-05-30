package model;

public class Customer {
    public int totalPurchasedTimes;
    public double totalMoneyPurchased;
    public Rank rank;

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

}
