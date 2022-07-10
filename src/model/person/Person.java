package model.person;

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
        System.out.print("Name: ");
        name = scanner.nextLine().trim();
        System.out.print("Age: ");
        age = Integer.parseInt(scanner.nextLine().trim());
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
