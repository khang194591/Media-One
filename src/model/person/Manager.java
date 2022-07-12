package model.person;

import model.*;
import model.product.Book;
import model.product.MovieDisc;
import model.product.MusicDisc;
import model.product.Product;
import util.Menu;

import java.util.*;

public class Manager extends Person {

    Warehouse warehouse;
    static final Scanner scanner = new Scanner(System.in);

    public Manager(String name, int age, Warehouse warehouse) {
        super(name, age);
        this.warehouse = warehouse;
    }

    public void addProduct() {
        Class tClass = Menu.selectProductType(); // book or movie or music disc
        if (tClass == null) {
            return;
        }
        Store store = warehouse.getStoreByType(tClass);
        boolean flag = true;
        List<Product> list = store.getList();

        if (tClass.equals(Book.class)) {
            Book newBook = new Book(scanner);
            for (Product item : list) {
                if (item.equalsName(newBook)) {
                    flag = false;
                    break;
                }
            } // end for
            if (flag) store.add(newBook);
        } else if (tClass.equals(MusicDisc.class)) {
            MusicDisc newProduct = new MusicDisc(scanner);
            for (Product item : list) {
                if (newProduct.getName().equals(item.getName())) {
                    flag = false;
                    break;
                }
            }
            if (flag) store.add(newProduct);
        } else {
            MovieDisc newProduct = new MovieDisc(scanner);
            for (Product item : list) {
                if (newProduct.getName().equals(item.getName())) {
                    flag = false;
                    break;
                }
            }
            if (flag) store.add(newProduct);
        } //
        if (!flag)
            System.out.println("Product is exits! Product have to difference name");
        if (flag)
            System.out.println("SUCCESS");
    }

    public void removeProduct() {
        Class tClass = Menu.selectProductType(); // book or movie or music disc
        Store store = warehouse.getStoreByType(tClass);

        System.out.print("Delete product by Name: ");
        List<Product> result = store.find(scanner.nextLine().trim());
        Menu.printTable(tClass, result);
        System.out.print("Confirm delete(Y/n): ");
        if (scanner.nextLine().toLowerCase().trim().equals("y")) {
            result.forEach(store::delete);
        }
    }

    public void setProduct() {
        Class tClass = Menu.selectProductType(); // book or movie or music disc
        Store store = warehouse.getStoreByType(tClass);

        System.out.print("Set product by index: ");
        int index = Integer.parseInt(scanner.nextLine());
        Product product = store.find(index);
        if (product == null) {
            System.out.println("Can't find product");
            return;
        }
        List<Product> printList = new ArrayList<Product>();
        printList.add(product);

        Menu.printTable(tClass, printList);
        Product newProduct = tClass.equals(Book.class) ? new Book(scanner) :
                tClass.equals(MusicDisc.class) ? new MusicDisc(scanner) :
                        new MovieDisc(scanner);
        store.setProduct(index, newProduct);
        System.out.print("Please enter something to continue: ");
        scanner.nextLine();
    }

    public void showProduct() {
        Class tClass = Menu.selectProductType(); // book or movie or music disc
        if (tClass == null) return;
        Store store = warehouse.getStoreByType(tClass);
        List<Product> list = store.getList();

        if (list == null || list.size() == 0) {
            System.out.println("Empty, please add more!!!");
        } else {
            Menu.printTable(tClass, list);
        }
        System.out.print("Please enter something to continue: ");
        scanner.nextLine();
    }

    public void addStaff(String name, int age, double salary, String username, String password) {
        Staff newStaff = new Staff(name, age, salary, username, password);
        warehouse.addStaff(newStaff);
    }

    public void findStaffByIndex() {

    }

    public void setStaff() {
        List<Staff> listStaff = warehouse.getListStaff();
        Staff newStaff;
        System.out.print("Index of staff:");
        int index = Integer.parseInt(scanner.nextLine());
        System.out.print("Tên nhân viên: ");
        String name = scanner.nextLine().trim();
        System.out.print("Tuổi nhân viên: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Lương nhân viên: ");
        double salary = Double.parseDouble(scanner.nextLine());
        System.out.print("Tên đăng nhập: ");
        String username = scanner.nextLine().trim();
        System.out.print("Mật khẩu: ");
        String password = scanner.nextLine().trim();
        newStaff = new Staff(name, age, salary, username, password);
        listStaff.set(index, newStaff);
    }

    public void deleteStaff() {
        List<Staff> listStaff = warehouse.getListStaff();
        Staff deleteStaff;
        System.out.print("Index of staff:");
        int index = Integer.parseInt(scanner.nextLine());
        if (index >= 0 && index < listStaff.size()) {
            deleteStaff = listStaff.get(index);
            warehouse.removeStaff(deleteStaff);
        } else {
            System.out.println("Invalid index!!!");
        }
    }

    public void showStaffs(List<Staff> listStaff) {
        if (listStaff == null || listStaff.size() == 0) {
            System.out.println("Empty, please add more!!!");
            return;
        }
        System.out.printf("|%10s| %20s | %20s | %20s |\n", "index", "name", "age", "salary");
        for (int i = 0; i < listStaff.size(); i++) {
            System.out.printf("|%10d%s\n", i, listStaff.get(i));
        }
    }

    public void paySalary() {
        Calendar nowDate = Calendar.getInstance();
        System.out.print("Pay salary this month " + (nowDate.get(Calendar.MONTH) + 1) + " (Y/N)?: ");
        if (scanner.nextLine().trim().toLowerCase().equals("y")) {
            List<Staff> listStaff = warehouse.getListStaff();
            MaintenanceFee newFee;
            String title = "Pay salary month " + (nowDate.get(Calendar.MONTH) + 1);
            String des = "For " + listStaff.size() + " staff";
            double totalMoney = 0;
            for (int i = 0; i < listStaff.size(); i++) {
                Staff staff = listStaff.get(i);
                totalMoney += staff.getSalary();
            }
            newFee = new MaintenanceFee(title, des, totalMoney, nowDate.getTime());
            newFee.show();
            warehouse.addMaintenanceFee(newFee);
        }
    }// end func
}
