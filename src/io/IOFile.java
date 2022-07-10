package io;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    static public void writeToFile(String path, Object obj) throws Exception {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.close();
    }

    static public Object readFormFile(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            ois.close();
            return obj;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}