package model;

public class Staff extends Person {
    private String id;
    private double salary;

    public Staff(String name, int age, String id, double salary) {
        super(name, age);
        this.id = id;
        this.salary = salary;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
