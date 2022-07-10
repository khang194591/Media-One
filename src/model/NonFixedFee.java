package model;

import java.util.Date;
import java.util.Scanner;

public class NonFixedFee extends Fee{
    public NonFixedFee(Scanner scanner){
        super(scanner);
    }
    public NonFixedFee(String title, String description, double totalMoney, Date date) {
        super(title, description, totalMoney, date);
    }
}
