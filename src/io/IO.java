package io;

import java.util.Scanner;

public class IO {
    static public String getString(Scanner scanner, String defaultValue, String message) {
        System.out.print(message);
        String buff;
        buff = scanner.nextLine().trim();

        if (!defaultValue.equals("") && buff.equals("")) {
            return defaultValue;
        }

        while (buff.equals("")) {
            System.out.println("Yêu cầu nhập vào xâu khác rỗng!");
            System.out.print(message);
            buff = scanner.nextLine().trim();
        }
        return buff;
    }

}
