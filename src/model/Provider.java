package model;

import java.util.List;

public class Provider {
    private String address;
    private String name;

    private List<Product> listProductProvide;// List of product on offer

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<Product> getListProductProvide() {
        return listProductProvide;
    }

    public void setListProductProvide(List<Product> listProductProvide) {
        this.listProductProvide = listProductProvide;
    }
}
