package model.person;

import io.IO;

import java.io.Serializable;
import java.util.Scanner;

public class Person implements Serializable {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(Scanner scanner) {
        name = IO.getString(scanner, "", "Tên: ");
        age = Integer.parseInt(IO.getString(scanner, "", "Tuổi: "));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
