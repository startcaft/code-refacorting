package com.startcaft.www;

import org.junit.Test;

import java.util.*;

/**
 * 为什么要用Lambda表达式以及Stream API。
 */
public class TestLambda {

    /**
     * 原来的匿名内部类，写法繁琐，而且很猥琐。
     */
    public void test01() {
        {
            Comparator<Integer> com = new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o1, o2);//真正有用的只有这句话。
                }
            };

            TreeSet<Integer> ts = new TreeSet<>(com);
        }
    }

    /**
     * Lambda 表达式
     */
    public void test02() {
        {
            Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

            TreeSet<Integer> ts = new TreeSet<>(com);
        }
    }

    /***********************************下面的代码有大量的冗余代码**************************************/
    List<Employee> emps = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 8888.88),
            new Employee("王五", 42, 18888.88),
            new Employee("赵六", 35, 12888.88),
            new Employee("田七", 32, 10888.88)
    );

    //需求：获取当前公司中员工年龄大于35的员工信息
    public List<Employee> filterEmployees(List<Employee> emps) {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : emps) {
            if (emp.getAge() >= 35) {
                list.add(emp);
            }
        }
        return list;
    }

    @Test
    public void testFilterEmployees() {
        List<Employee> employeeList = filterEmployees(emps);
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }
    }

    //需求：获取当前公司中员工工资大于 5000 的员工信息
    public List<Employee> filterEmployeesAge(List<Employee> emps) {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : emps) {
            if (emp.getSalary() >= 5000) {
                list.add(emp);
            }
        }
        return list;
    }

    @Test
    public void testFilterEmployeesAge() {
        List<Employee> employeeList = filterEmployeesAge(emps);
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }
    }

    /***优化方式一 ： 设计模式之策略模式***/
    /***缺点：策略如果过多，实现类会很多***/
    public List<Employee> filterEmp(List<Employee> list, MyPredicate<Employee> predicate) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (predicate.test(emp)) {
                emps.add(emp);
            }
        }
        return emps;
    }

    @Test
    public void testFilterEmp() {
        List<Employee> list = filterEmp(emps, new FilterEmployeeByAge());
        for (Employee emp : list) {
            System.out.println(emp);
        }

        System.out.println("------------------------------------");

        list = filterEmp(emps, new FilterEmployeeBySalary());
        for (Employee emp : list) {
            System.out.println(emp);
        }
    }

    /***优化方式二 ： 匿名内部类***/
    /***缺点 ： 可读性降低，改变了代码的结构，代码冗余***/
    @Test
    public void testFilterEmpInnerClass(){
        List<Employee> list = filterEmp(emps, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() <= 10000;
            }
        });

        for (Employee emp : list) {
            System.out.println(emp);
        }
    }

    /***优化方式三 ： Lambda表达式***/
    @Test
    public void testFilterEmpLambda(){
        List<Employee> list = filterEmp(emps,(employee) -> employee.getSalary() <= 10000 );
        list.forEach(System.out::println);
    }

    /***优化方式三 ： Stream API***/
    @Test
    public void testFilterEmpStream(){
        emps.stream()
                .filter((e) -> e.getSalary() <= 10000)
                .forEach(System.out::println);
    }
}
