package util;

import io.*;
import model.*;
import model.person.Manager;
import model.person.Person;
import model.person.Staff;
import model.product.Book;
import model.product.MovieDisc;
import model.product.MusicDisc;
import model.product.Product;

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
                    username = IO.getString(scanner, "", "Tên đăng nhập: ");
                    password = IO.getString(scanner, "", "Mật khẩu: ");
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
        while (true) {
            System.out.println("\n----------\n");
            System.out.println("1. Tạo hóa đơn mới");
            System.out.println("2. Phí phát sinh");
            System.out.println("3. Quay lại!");
            System.out.print("Lựa chọn:");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                option = 0;
            }
            switch (option) {
                case 1 -> creatBill();
                case 2 -> {
                    System.out.println("Phí phát sinh.");
                    reportIncurred();
                }
                case 3 -> {
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!!!");
            }
        }// end while
    }

    private static void creatBill() {
        int option;
        Class type;
//      Cần  sửa thành tìm tới Staff đang đăng nhập và nhập thông tin khách hàng
        Staff staff = (Staff) user;
        Customer newCustomer;
        Bill newBill;
        CartItem newCart;
        Product newProduct;
        int quantity;
        System.out.println("Thông tin khách hàng");
        newCustomer = new Customer(scanner);
        System.out.println("Tên khách hàng: " + newCustomer.getName());
        // creat new bill
        newBill = new Bill(staff, newCustomer);
        while (true) {
            System.out.println("\n\n----------\n\n");
            System.out.println("1. Hiện thị tất cả sản phẩm");
            System.out.println("2. Tìm kiếm sản phẩm");
            System.out.println("3. Thêm sản phẩm vào hóa đơn");
            System.out.println("4. Xem lại hóa đơn");
            System.out.println("5. Thanh toán hóa đơn");
            System.out.println("6. Hủy hóa đơn");
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
                    quantity = Integer.parseInt(IO.getString(scanner, "", "Số lượng: "));
                    newCart = new CartItem(newProduct, quantity, newProduct.getSellPrice());
                    newBill.addCartItem(newCart);
                }
                case 4 -> {
                    System.out.println("Xem lại hóa đơn");
                    newBill.showInfor();
                }
                case 5 -> {
                    System.out.println("Thanh toán hóa đơn");
                    warehouse.addListBill(newBill);
                    return;
                }
                case 6 -> {
                    System.out.println("Hủy hóa đơn");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!!!");
            }
        }// end while
    }// end func

    private static void reportIncurred() {
        NonFixedFee newFee;
        Date date = new Date();
        String title, description;
        double totalMoney;
        title = IO.getString(scanner, "", "Kiểu phí: ");
        totalMoney = Double.parseDouble(IO.getString(scanner, "", "Tổng tiền: "));
        description = IO.getString(scanner, "", "Nội dung miêu tả (thông tin phí, số lượng): ");
        System.out.println("Ngày tháng năm: " + date);
        System.out.print("Xác nhận(Y/n): ");
        if (scanner.nextLine().toLowerCase().trim().equals("y")) {
            newFee = new NonFixedFee(title, description, totalMoney, date);
            newFee.show();
            warehouse.addNonFixedFee(newFee);
        } else {
            System.out.print("Xác nhận hủy!");
            newFee = null;
        }
    }// end func

    static public void managerMenu() {
        int option = 0;
        while (true) {
            System.out.println("\n----------\n");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("3. Thống kê doanh thu");
            System.out.println("4. Xem phí không cố định");
            System.out.println("5. Xem phí duy trì");
            System.out.println("6. Danh sách hóa đơn");
            System.out.println("7. Quay lại!");
            System.out.print("Lựa chọn:");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                option = 0;
            }
            switch (option) {
                case 1 -> manageProducts();
                case 2 -> manageStaffs();
                case 3 -> revenueStatistics();
                case 4 -> {
                    System.out.println("Phí không cố định");
                    for (Fee item : warehouse.getListNonFixedFee()) {
                        System.out.println("" + item);
                    }
                }
                case 5 -> {
                    System.out.println("Phí cố định");
                    for (Fee item : warehouse.getListMaintenanceFee()) {
                        System.out.println("" + item);
                    }
                }
                case 6 -> {
                    System.out.println("Hiện thị danh sách hóa đơn");
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
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Xóa sản phẩm");
            System.out.println("3. Sửa thông tin sản phẩm");
            System.out.println("4. Danh sách sản phẩm");
            System.out.println("5. Quay lại");
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
                    break;
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
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Cập nhật thông tin nhân viên");
            System.out.println("3. Danh sách nhân viên");
            System.out.println("4. Thanh toán lương");
            System.out.println("5. Quay lại");
            System.out.print("Lựa chọn: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                option = 0;
            }
            switch (option) {
                case 1:
                    String name = IO.getString(scanner, "", "Tên nhân viên: ");
                    int age = Integer.parseInt(IO.getString(scanner, "", "Tuổi nhân viên: "));
                    double salary = Double.parseDouble(IO.getString(scanner, "", "Lương nhân viên: "));
                    String username = IO.getString(scanner, "", "Tên đăng nhập: ");
                    String password = IO.getString(scanner, "", "Mật khẩu: ");
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
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!!!");
                    break;
            }
        }
    } // end func

    static public void revenueStatistics() {
        int option = 0;
        int startMonth, startYear, endMonth, endYear;
        while (option != 3) {
            System.out.println("\n----------\n");
            System.out.println("1. Theo tháng");
            System.out.println("2. Theo năm");
            System.out.println("3. Quay lại");
            System.out.print("Lựa chọn: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                option = 0;
            }
            switch (option) {
                case 1:
                    startMonth = Integer.parseInt(IO.getString(scanner, "", "Tháng bắt đầu: "));
                    startYear = Integer.parseInt(IO.getString(scanner, "", "Năm bắt đầu: "));
                    endMonth = Integer.parseInt(IO.getString(scanner, "", "Tháng kết thúc: "));
                    endYear = Integer.parseInt(IO.getString(scanner, "", "Năm kết thúc: "));
                    warehouse.revenueStatistics(startMonth, startYear, endMonth, endYear);
                    break;
                case 2:
                    startYear = Integer.parseInt(IO.getString(scanner, "", "Năm bắt đầu: "));
                    endYear = Integer.parseInt(IO.getString(scanner, "", "Năm kết thúc: "));
                    warehouse.revenueStatistics(0, startYear, 0, endYear);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!!!");
            }
        }
    } // end func


    static public Class selectProductType() {
        int option = 0;
        while (true) {
            System.out.println("\n\n----------\n\n");
            System.out.println("1. Sách");
            System.out.println("2. Đĩa phim");
            System.out.println("3. Đĩa nhạc");
            System.out.println("4. Quay lại");
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
    }

    private static Store getStoreProduct(Class tClass) {
        return tClass.equals(Book.class) ? warehouse.getBookStore() : tClass.equals(MusicDisc.class) ? warehouse.getMusicDiscStore() : tClass.equals(MovieDisc.class) ? warehouse.getMovieDiscStore() : null;
    }

    private static void showProducts(Class tClass) {
        if (tClass == null) return;
        Store store = getStoreProduct(tClass);
        List<Product> list = store.getList();
        if (list == null || list.size() == 0) {
            System.out.println("Rỗng, vui lòng thêm!!!");
        } else {
            printTable(tClass, list);
        }
        System.out.print("Xin mời nhập phím bất kỳ để tiếp tục: ");
        scanner.nextLine();
    }

    private static void findProductByName(Class tClass) {
        if (tClass == null) return;
        Store store = getStoreProduct(tClass);
        List<Product> list = store.getList();
        List<Product> result = store.find(IO.getString(scanner, "", "Tên sản phẩm: "));
        printTable(tClass, result);
        System.out.print("Xin mời nhập phím bất kỳ để tiếp tục: ");
        scanner.nextLine();
    }

    private static Product findOneProductByName(Class tClass) {
        if (tClass == null) return null;
        Store store = getStoreProduct(tClass);
        List<Product> list = store.getList();
        List<Product> result = store.find(IO.getString(scanner, "", "Tên sản phẩm: "));
        System.out.println("" + result.get(0));
        if (result.size() == 0) return null;
        return list.get(0);
    }

    private static void findProductByIndex(Class tClass) {
        if (tClass == null) return;
        Store store = getStoreProduct(tClass);
        List<Product> list = store.getList();
        List<Product> result = store.find(IO.getString(scanner, "", "Index: "));
        printTable(tClass, result);
        System.out.print("Xin mời nhập phím bất kỳ để tiếp tục: ");
        scanner.nextLine();
    }

    public static void printTable(Class tClass, List<Product> result) {
        if (result == null || result.size() == 0) {
            System.out.println("Rỗng, vui lòng nhập thêm!!!");
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
