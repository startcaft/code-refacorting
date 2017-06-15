package com.startcaft.www;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * StreamApi 练习
 */
public class TestStreamApi3 {

    List<Employee> emps = Arrays.asList(
            new Employee("张三", 18, 9999.99, Employee.Status.BUSY),
            new Employee("李四", 38, 8888.88,Employee.Status.FREE),
            new Employee("王五", 42, 18888.88,Employee.Status.FREE),
            new Employee("赵六", 35, 12888.88, Employee.Status.VOCATION),
            new Employee("田七", 32, 10888.88,Employee.Status.VOCATION),
            new Employee("田七", 32, 10888.88,Employee.Status.VOCATION)
    );

    /**
     * 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
     * [1,2,3,4,5] 返回 [1,4,3,16,25]
     */
    @Test
    public void test(){

        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.stream()
                .map((x) -> x * x)
                .forEach(System.out::println);
    }

    /**
     * 如何使用 map-reduce 方法数一数流中有多少个Employee呢？
     */
    @Test
    public void test2(){

        Optional<Integer> optional = emps.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        System.out.println(optional.get());
    }
}
