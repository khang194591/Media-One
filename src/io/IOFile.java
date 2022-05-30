package io;

import model.Product;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    static public void writeToFile(String path, List<Product> list) throws Exception {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
    }

    static public List<Product> readFormFile(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Product> list = (List<Product>) ois.readObject();
            ois.close();
            return list;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}

/*
 * Lưu ý:
 * - public FileOutputStream(String filename, boolean append) lựa chọn ghi đè hoặc ghi tiếp
 * - Cần có throws Exception khi thao tác vào ra file
 * - Điều kiện ghi file Object: thỏa mãn quá trình tuần tự serialization
 * - Quá trình tuần thự thỏa mãn khi đủ 3 đk:
 * + Lớp đó phải được khai báo public
 * + Lớp đó phải thực thi giao diện Serializable (implements Serializable)
 * + Phải có một phương thức khởi tạo không tham số
 * + Tất cả các thành phần của lớp đó phải có thể tuần tự hóa được:
 * ## Hoặc là kiểu nguyên thủy hoặc là các đối tượng Serializable
 */
