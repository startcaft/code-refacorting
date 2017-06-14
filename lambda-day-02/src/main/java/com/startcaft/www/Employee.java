package com.startcaft.www;

/**
 * Created by startcaft on 2017/6/9.
 */
public class Employee {

    private String name;
    private Integer age;
    private double salary;
    private Status stauts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Status getStauts() {
        return stauts;
    }

    public void setStauts(Status stauts) {
        this.stauts = stauts;
    }

    public Employee(){}

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, Integer age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee(String name, Integer age, double salary, Status stauts) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.stauts = stauts;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", stauts=" + stauts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (Double.compare(employee.salary, salary) != 0) return false;
        if (!name.equals(employee.name)) return false;
        return age.equals(employee.age);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + age.hashCode();
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public enum Status{
        FREE,
        BUSY,
        VOCATION;
    }
}
