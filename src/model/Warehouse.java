package model;

import io.IOFile;
import model.person.Staff;
import model.product.Book;
import model.product.MovieDisc;
import model.product.MusicDisc;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private final Store bookStore;
    private final Store musicDiscStore;
    private final Store movieDiscStore;
    private final List<Bill> listBill; // list of paid bills
    private List<Staff> listStaff; // list of staffs

    private final List<MaintenanceFee> listMaintenanceFee; // list of MaintenanceFee
    private final List<NonFixedFee> listNonFixedFee; // list of NonFixedFee
    private double profit; //
    private int soldNumber;

    public Warehouse() {
        bookStore = new Store(Book.class);
        musicDiscStore = new Store(MusicDisc.class);
        movieDiscStore = new Store(MovieDisc.class);
        listBill = (List<Bill>) IOFile.readFormFile("data/bills.txt");
        listStaff = (List<Staff>) IOFile.readFormFile("data/staffs.txt");
        listMaintenanceFee = (List<MaintenanceFee>) IOFile.readFormFile("data/maintenanceFees.txt");
        listNonFixedFee = (List<NonFixedFee>) IOFile.readFormFile("data/nonFixedFees.txt");
    }

    // add


    // thao tac voi Bill
    public void addListBill(Bill bill) {
        if (bill == null) return;
        if (bill.getQtyOrdered() == 0) return;
        listBill.add(bill);
    }

    public List<Bill> getListBill() {
        return listBill;
    }

    public List<Staff> getListStaff() {
        return listStaff;
    }

    public void setListStaff(List<Staff> listStaff) {
        this.listStaff = listStaff;
    }

    public void addStaff(Staff newStaff) {
        if (newStaff == null) return;
        listStaff.add(newStaff);
    }

    public void removeStaff(Staff staff) {
        if (staff != null) {
            listStaff.remove(staff);
        }
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

    public List<Fee> getListMaintenanceFee() {
        List<Fee> list = new ArrayList<>();
        for (MaintenanceFee item : listMaintenanceFee) {
            list.add(item);
        }
        return list;
    }

    public List<Fee> getListNonFixedFee() {
        List<Fee> list = new ArrayList<>();
        for (NonFixedFee item : listNonFixedFee) {
            list.add(item);
        }
        return list;
    }

    public Store getStoreByType(Class tClass) {
        return tClass.equals(Book.class) ? this.bookStore :
                tClass.equals(MusicDisc.class) ? this.musicDiscStore :
                        tClass.equals(MovieDisc.class) ? this.movieDiscStore : null;
    }

    // Phuong thuc nay hoi ngu
    public void showBill(Bill bill) {
        if (listBill.contains(bill)) bill.showInfor();
    }

    // just have addFunction MaintenanceFee
    public void addMaintenanceFee(MaintenanceFee newFee) {
        listMaintenanceFee.add(newFee);
    }

    public void addNonFixedFee(NonFixedFee newFee) {
        listNonFixedFee.add(newFee);
    }

    public List<MaintenanceFee> toMaintenanceFee(List<Fee> list) {
        List<MaintenanceFee> updateList = new ArrayList<>();
        for (Fee item : list) {
            updateList.add((MaintenanceFee) item);
        }
        return updateList;
    }

    public List<NonFixedFee> toNonFixedFee(List<Fee> list) {
        List<NonFixedFee> updateList = new ArrayList<>();
        for (Fee item : list) {
            updateList.add((NonFixedFee) item);
        }
        return updateList;
    }

    public double allMoneyFormBill(int month, int year) {
        double total = 0;
        if (month == 0 && year == 0) {
            for (Bill bill : listBill) {
                total += bill.totalCost();
            }
            return total;
        }
        if (month == 0) {
            for (Bill bill : listBill) {
                if (bill.getYear() == year) {
                    total += bill.totalCost();
                }
            }
            return total;
        }
        if (year != 0) {
            for (Bill bill : listBill) {
                if (bill.getYear() == year && bill.getMonth() == month) {
                    total += bill.totalCost();
                }
            }
            return total;
        }
        return 0;
    }

    public double allNonFixedFee(int month, int year) {
        double total = 0;
        if (month == 0 && year == 0) {
            for (NonFixedFee item : listNonFixedFee) {
                total += item.getTotalMoney();
            }
            return total;
        }
        if (month == 0) {
            for (NonFixedFee item : listNonFixedFee) {
                if (item.getYear() == year) {
                    total += item.getTotalMoney();
                }
            }
            return total;
        }
        if (year != 0) {
            for (NonFixedFee item : listNonFixedFee) {
                if (item.getYear() == year && item.getMonth() == month) {
                    total += item.getTotalMoney();
                }
            }
            return total;
        }
        return 0;
    }

    public double allMaintenanceFee(int month, int year) {
        double total = 0;
        if (month == 0 && year == 0) {
            for (MaintenanceFee item : listMaintenanceFee) {
                total += item.getTotalMoney();
            }
            return total;
        }
        if (month == 0) {
            for (MaintenanceFee item : listMaintenanceFee) {
                if (item.getYear() == year) {
                    total += item.getTotalMoney();
                }
            }
            return total;
        }
        if (year != 0) {
            for (MaintenanceFee item : listMaintenanceFee) {
                if (item.getYear() == year && item.getMonth() == month) {
                    total += item.getTotalMoney();
                }
            }
            return total;
        }
        return 0;
    }


    public void revenueStatistics(int startMonth, int startYear, int endMonth, int endYear) {
        List<Double> array = new ArrayList<>(); // save each profit
        int count;
        double profit = 0; // save all profit
        double buffer = 0; // save each profit
        double allMoneyFormBill = 0,
                allNonFixedFee = 0,
                allMaintenanceFee = 0;
        if (startMonth == 0 && endMonth == 0) { // thống kê theo từng năm
            System.out.println("Tổng chi");
            System.out.printf("%40s|%40s|%50s\n", "Nội dung", "Số tiền", "Thời gian");
            for (int i = startYear; i <= endYear; i++) {
                System.out.printf("%40s|%40s|%50s\n", "NonFixedFee + MaintenanceFee",
                        allNonFixedFee(0, i) + allMaintenanceFee(0, i),
                        i);
                // save each profit
                array.add((allNonFixedFee(0, i) + allMaintenanceFee(0, i)));
                profit -= (allNonFixedFee(0, i) + allMaintenanceFee(0, i));
            }
            System.out.println("Tổng thu");
            System.out.printf("%40s|%40s|%50s\n", "Nội dung", "Số tiền", "Thời gian");

            count = 0;
            for (int i = startYear; i <= endYear; i++) {
                System.out.printf("%40s|%40s|%50s\n", "Thu từ khách hàng",
                        allMoneyFormBill(0, i),
                        i);
                profit += allMoneyFormBill(0, i);
                // save each profit
                array.set(count, array.get(count) + allMoneyFormBill(0, i));
                count++;
            }
            System.out.println("Tổng doanh thu: " + profit);
//             System.out.println("Bảng thống kê");
//            buffer = Collections.max(array);
//            System.out.println("Max value: " + buffer);
        } else { // thống kê theo từng tháng
            int i, j;
            System.out.println("Tổng chi");
            System.out.printf("%40s|%40s|%50s\n", "Nội dung", "Số tiền", "Thời gian");
            for (i = startYear; i <= endYear; i++) {
                for (j = startMonth; j <= endMonth && i <= endYear; j++) {
                    System.out.printf("%40s|%40s|%50s\n", "NonFixedFee + MaintenanceFee",
                            allNonFixedFee(i, j) + allMaintenanceFee(i, j),
                            "Tháng " + i + "Năm" + j);
                    profit -= (allNonFixedFee(0, i) + allMaintenanceFee(0, i));
                }//end for month
            }// end for year
            System.out.println("Tổng thu");
            System.out.printf("%40s|%40s|%50s\n", "Nội dung", "Số tiền", "Thời gian");
            for (i = startYear; i <= endYear; i++) {
                for (j = startMonth; j <= endMonth; j++) {
                    System.out.printf("%40s|%40s|%50s\n", "Thu từ khách hàng",
                            allMoneyFormBill(i, j),
                            "Tháng " + i + "Năm" + j);
                    profit += allMoneyFormBill(0, i);
                }//end for month
            }// end for year
        }
    }
}//end class
