package model;

import io.IOFile;
import model.product.Book;
import model.product.MovieDisc;
import model.product.MusicDisc;
import model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private int totalQuantity;
    private List<Product> list;

    public Store(Class tClass) {
        if (Book.class.isAssignableFrom(tClass)) {
            list = (List<Product>) IOFile.readFormFile("data/book.txt");
        } else if (MovieDisc.class.isAssignableFrom(tClass)) {
            list = (List<Product>) IOFile.readFormFile("data/movieDisc.txt");
        } else if (MusicDisc.class.isAssignableFrom(tClass)) {
            list = (List<Product>) IOFile.readFormFile("data/musicDisc.txt");
        }
    }

    public List<Product> find(String name) {
        List<Product> result = new ArrayList<>();
        for (Product item : list) {
            String itemName = item.getName().toLowerCase();
            if (itemName.contains(name.toLowerCase())) {
                result.add(item);
            }
        }
        return result;
    }

    public Product find(int index) {
        int i = 0;
        for (Product item : list) {
            if (i++ == index) {
                return item;
            }
        }
        return null;
    }

    public void add(Product product) {
        totalQuantity += product.getQuantity();
        list.add(product);
        System.out.println("Tổng số lượng: " + totalQuantity);
    }



    public void delete(Product product) {
        list.remove(product);
    }

    public void delete(int index) {
        list.remove(index);
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setProduct(int index, Product product) {
         list.set(index, product);
    }

    public List<Product> getList() {
        return list;
    }
}
