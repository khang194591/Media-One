package util;

import io.*;
import model.*;
import model.person.Manager;
import model.person.Person;
import model.person.Staff;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    static final Scanner scanner = new Scanner(System.in);
    static final Warehouse warehouse = new Warehouse();
    static Person user;

    static public void showMainMenu() {// cần chỉnh sửa ghi ra file
        int option = 0;

        while (option != 2) {
            System.out.println("\n----------\n");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Thoát");
            System.out.print("Lựa chọn: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Lựa chọn không hợp lệ!!!");
            }
            switch (option) {
                case 1 -> {
                    String username;
                    String password;
                    System.out.println("\n--------ĐĂNG NHẬP--------\n");
                    System.out.print("Tên đăng nhập: ");
                    username = scanner.nextLine().trim();
                    System.out.print("Mật khẩu: ");
                    password = scanner.nextLine().trim();
                    if (username.equals("admin") && password.equals("admin")) {
                        user = new Manager("admin", 20, warehouse);
                        managerMenu();
                    } else {
                        List<Staff> staffs = (List<Staff>) IOFile.readFormFile("data/staffs.txt");
                        for (Staff staff : staffs) {
                            if (staff.getUsername().equals(username) && staff.getPassword().equals(password)) {
                                user = staff;
                                staffMenu();
                                break;
                            }
                        }
                        System.out.println("Sai mật khẩu hoặc tên đăng nhập");
                    }
                }
                case 2 -> {
                    // write data to file
                    try {
                        IOFile.writeToFile("data/book.txt", warehouse.getBookStore().getList());
                        IOFile.writeToFile("data/musicDisc.txt", warehouse.getBookStore().getList());
                        IOFile.writeToFile("data/movieDisc.txt", warehouse.getBookStore().getList());
                        IOFile.writeToFile("data/staffs.txt", warehouse.getListStaff());
                        IOFile.writeToFile("data/bills.txt", warehouse.getListBill());
                        IOFile.writeToFile("data/maintenanceFees.txt", warehouse.getListMaintenanceFee());
                        IOFile.writeToFile("data/nonFixedFees.txt", warehouse.getListNonFixedFee());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Có lỗi khi lưu dữ liệu");
                    }
                }
                default -> System.out.println("Lựa chọn không hợp lệ!!!");
            }
        }
    }

    static public void staffMenu() {
        int option = 0;
        while (option != 3) {
            System.out.println("\n----------\n");
            System.out.println("1. Create new bill");
            System.out.println("2. Report fees incurred");
            System.out.println("3. Return!");
            System.out.print("Lựa chọn:");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                option = 0;
            }
            switch (option) {
                case 1 -> {
                    creatBill();
                }
                case 2 -> {
                    System.out.println("Report fees incurred.");
                    reportIncurred();
                }
                case 3 -> {
                    return;
                }
                default -> {
                    System.out.println("Lựa chọn không hợp lệ!!!");
                }
            }
        }// end while
    }

    private static void creatBill() {
        int option = 0;
        Class type = null;
//      Cần  sửa thành tìm tới Staff đang đăng nhập và nhập thông tin khách hàng
        Staff staff = (Staff) user;
        Customer newCustomer;
        Bill newBill;
        CartItem newCart;
        Product newProduct;
        int quantity;
        System.out.println("Information of customer");
        newCustomer = new Customer(scanner);
        System.out.println("Name: " + newCustomer.getName());
        // creat new bill
        newBill = new Bill(staff, newCustomer);
        while (option != 6 || option != 5) {
            System.out.println("\n\n----------\n\n");
            System.out.println("1. Show All products");
            System.out.println("2. Find product");
            System.out.println("3. Add product to bill");
            System.out.println("4. Review Bill");
            System.out.println("5. Pay the bill");
            System.out.println("6. Delete the bill");
            System.out.print("Lựa chọn:");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                option = 0;
            }
            switch (option) {
                case 1 -> {
                    // có thể tạo 1 function xem sản phẩm cho khách hàng và nhân viên khác với manager
//                    user.showProduct();
                }
                case 2 -> {
                    type = selectProductType();
                    findProductByName(type);
                }
                case 3 -> {
                    // find one product
                    type = selectProductType();
                    newProduct = findOneProductByName(type);
                    if (newProduct == null) break;
                    System.out.print("Quantity: ");
                    quantity = Integer.parseInt(scanner.nextLine().trim());
                    newCart = new CartItem(newProduct, quantity, newProduct.getSellPrice());
                    newBill.addCartItem(newCart);
                }
                case 4 -> {
                    System.out.println("Review bill");
                    newBill.showInfor();
                }
                case 5 -> {
                    System.out.println("Pay the bill");
                    warehouse.addListBill(newBill);
                    return;
                }
                case 6 -> {
                    System.out.println("Delete the bill");
                    newBill = null;
                    return;
                }
                default -> {
                    System.out.println("Lựa chọn không hợp lệ!!!");
                }
            }
        }// end while
    }// end func

    private static void reportIncurred() {
        NonFixedFee newFee;
        Date date = new Date();
        String title, description;
        double totalMoney;
        System.out.print("Kiểu phí: ");
        title = scanner.nextLine().trim();
        System.out.print("Tổng tiền: ");
        totalMoney = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Nội dung miêu tả (thông tin phí, số lượng): ");
        description = scanner.nextLine().trim();
        System.out.println("Ngày tháng năm: " + date);
        System.out.print("Confirm report(Y/n): ");
        if (scanner.nextLine().toLowerCase().trim().equals("y")) {
            newFee = new NonFixedFee(title, description, totalMoney, date);
            newFee.show();
            warehouse.addNonFixedFee(newFee);
        } else {
            System.out.print("Confirm don't report! Delete report!");
            newFee = null;
        }
    }// end func

    static public void managerMenu() {
        int option = 0;
        while (option != 7) {
            System.out.println("\n----------\n");
            System.out.println("1. Manage products");
            System.out.println("2. Manage staffs");
            System.out.println("3. Revenue statistics");
            System.out.println("4. View Non-Fixed Fee");
            System.out.println("5. View maintenance Fee");
            System.out.println("6. Show Bill");
            System.out.println("7. Return!");
            System.out.print("Lựa chọn:");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                option = 0;
            }
            switch (option) {
                case 1 -> {
                    manageProducts();
                }
                case 2 -> {
                    manageStaffs();
                }
                case 3 -> {
                    revenueStatistics();
                }
                case 4 -> {
                    System.out.println("Non-fixed Fee");
                    for (Fee item : warehouse.getListNonFixedFee()) {
                        System.out.println("" + item);
                    }
                }
                case 5 -> {
                    System.out.println("Maintenance Fee");
                    for (Fee item : warehouse.getListMaintenanceFee()) {
                        System.out.println("" + item);
                    }
                }
                case 6 -> {
                    System.out.println("Show bill");
                    warehouse.showAllBill();
                }
                case 7 -> {
                    return;
                }
                default -> {
                    System.out.println("Lựa chọn không hợp lệ!!!");
                }
            }
        }// end while
    }

    static public void manageProducts() {
        int option = 0;
        Manager user = (Manager) Menu.user;
        while (option != 5) {
            System.out.println("\n----------\n");
            System.out.println("1. Add product");
            System.out.println("2. Remove product");
            System.out.println("3. Set product");
            System.out.println("4. Show product");
            System.out.println("5. Return");
            System.out.print("Lựa chọn: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                option = 0;
            }
            switch (option) {
                case 1:
                    user.addProduct();
                    break;
                case 2:
                    user.removeProduct();
                    break;
                case 3:
                    user.setProduct();
                    break;
                case 4:
                    user.showProduct();
                    break;
                case 5:
                default:
                    System.out.println("Lựa chọn không hợp lệ!!!");
            }
        }
    } // end func

    static public void manageStaffs() {
        int option = 0;
        Manager manager = (Manager) user;
        while (option != 5) {
            System.out.println("\n----------\n");
            System.out.println("1. Add staff");
            System.out.println("2. Update information of staff");
            System.out.println("3. Show staffs");
            System.out.println("4. Pay wage");
            System.out.println("5. Return ");
            System.out.print("Lựa chọn: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                option = 0;
            }
            switch (option) {
                case 1:
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
                    manager.addStaff(name, age, salary, username, password);
                    break;
                case 2:
                    manager.setStaff();
                    break;
                case 3:
                    manager.showStaffs(warehouse.getListStaff());
                    break;
                case 4:
                    manager.paySalary();
                    break;
                case 5:
                default:
                    System.out.println("Lựa chọn không hợp lệ!!!");
            }
        }
    } // end func

    static public void revenueStatistics() {
        int option = 0;
        int startMonth, startYear, endMonth, endYear;
        while (option != 3) {
            System.out.println("\n----------\n");
            System.out.println("1. By month");
            System.out.println("2. By year");
            System.out.println("3. Return");
            System.out.print("Lựa chọn: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                option = 0;
            }
            switch (option) {
                case 1:
                    System.out.print("Input start month: ");
                    startMonth = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Input start year: ");
                    startYear = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Input end month: ");
                    endMonth = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Input end year: ");
                    endYear = Integer.parseInt(scanner.nextLine().trim());
                    warehouse.revenueStatistics(startMonth, startYear, endMonth, endYear);
                    break;
                case 2:
                    System.out.print("Input start year: ");
                    startYear = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Input end year: ");
                    endYear = Integer.parseInt(scanner.nextLine().trim());
                    warehouse.revenueStatistics(0, startYear, 0, endYear);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!!!");
            }
        }
    } // end func


    static public Class selectProductType() {
        int option = 0;
        while (option != 4) {
            System.out.println("\n\n----------\n\n");
            System.out.println("1. Book");
            System.out.println("2. Movie Disc");
            System.out.println("3. Music Disc");
            System.out.println("4. Return");
            System.out.print("Lựa chọn: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                option = 0;
            }
            switch (option) {
                case 1:
                    return Book.class;
                case 2:
                    return MovieDisc.class;
                case 3:
                    return MusicDisc.class;
                case 4:
                    return null;
                default:
                    System.out.println("Lựa chọn không hợp lệ!!!");
            }
        }
        return null;
    }

    private static Store getStoreProduct(Class tClass) {
        Store store = tClass.equals(Book.class) ? warehouse.getBookStore() : tClass.equals(MusicDisc.class) ? warehouse.getMusicDiscStore() : tClass.equals(MovieDisc.class) ? warehouse.getMovieDiscStore() : null;
        return store;
    }

    private static void showProducts(Class tClass) {
        if (tClass == null) return;
        Store store = getStoreProduct(tClass);
        List<Product> list = store.getList();
        if (list == null || list.size() == 0) {
            System.out.println("Empty, please add more!!!");
        } else {
            printTable(tClass, list);
        }
        System.out.print("Please enter something to continue: ");
        scanner.nextLine();
    }

    private static void findProductByName(Class tClass) {
        if (tClass == null) return;
        Store store = getStoreProduct(tClass);
        List<Product> list = store.getList();
        System.out.print("Name: ");
        List<Product> result = store.find(scanner.nextLine().trim());
        printTable(tClass, result);
        System.out.print("Please enter something to continue: ");
        scanner.nextLine();
    }

    private static Product findOneProductByName(Class tClass) {
        if (tClass == null) return null;
        Store store = getStoreProduct(tClass);
        List<Product> list = store.getList();
        System.out.print("Name: ");
        List<Product> result = store.find(scanner.nextLine().trim());
        System.out.println("" + result.get(0));
        if (result.size() == 0) return null;
        return list.get(0);
    }

    private static void findProductByIndex(Class tClass) {
        if (tClass == null) return;
        Store store = getStoreProduct(tClass);
        List<Product> list = store.getList();
        System.out.print("Index: ");
        List<Product> result = store.find(scanner.nextLine().trim());
        printTable(tClass, result);
        System.out.print("Please enter something to continue: ");
        scanner.nextLine();
    }

    public static void printTable(Class tClass, List<Product> result) {
        if (result == null || result.size() == 0) {
            System.out.println("Empty, please add more!!!");
            return;
        }
        if (tClass.equals(Book.class)) {
            System.out.printf("| %5s | %40s | %40s | %10s | %10s | %10s | %20s | %20s |\n", "index", "code", "name", "quantity", "import", "sell", "publisher", "author");
        } else if (tClass.equals(MusicDisc.class)) {
            System.out.printf("| %5s | %40s | %40s | %10s | %10s | %10s | %20s | %20s |\n", "index", "code", "name", "quantity", "import", "sell", "producer", "singer");
        } else {
            System.out.printf("| %5s | %40s | %40s | %10s | %10s | %10s | %20s | %20s |\n", "index", "code", "name", "quantity", "import", "sell", "producer", "director");
        }
        int index = 0;
        for (Object item : result) {
            System.out.printf("| %5d |%s\n", index++, item);
        }
    }
}
