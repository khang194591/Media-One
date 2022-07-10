package model;

import java.util.Date;
import java.util.Scanner;

// phí duy trì cừa hàng cố  định sửa chữa máy móc, tiền điện nước, tiền thuê mặt bằng
public class MaintenanceFee extends Fee{
    public MaintenanceFee(Scanner scanner){
        super(scanner);
    }
    public MaintenanceFee(String title, String description, double totalMoney, Date date) {
        super(title, description, totalMoney, date);
    }
}
