package com.startcaft.www;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by startcaft on 2017/6/14.
 */
public class TestStreamApi2 {

    List<Employee> emps = Arrays.asList(
            new Employee("张三", 18, 9999.99, Employee.Status.BUSY),
            new Employee("李四", 38, 8888.88,Employee.Status.FREE),
            new Employee("王五", 42, 18888.88,Employee.Status.FREE),
            new Employee("赵六", 35, 12888.88, Employee.Status.VOCATION),
            new Employee("田七", 32, 10888.88,Employee.Status.VOCATION),
            new Employee("田七", 32, 10888.88,Employee.Status.VOCATION)
    );

    /**还是中间操作
     * 规约
     * reduce(T identity,BinaryOperator) / reduce(BinaryOperator) ： 可以将流中元素反复结合起来，得到一个值
     */
    @Test
    public void test(){

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);//identity 作为x的实参，集合中的第一个元素作为y的实参 然后进行反复计算。

        System.out.println(sum);

        System.out.println("-----------------------------");

        //计算工资总和。map-reduce 模式 大数据常用
        Optional<Double> optional = emps.stream()
                .map((e) -> e.getSalary())
                .reduce(Double::sum);//没有起始值，所以有可能为空，所以返回Optional<T>，避免空指针
        System.out.println(optional.get());
    }

    /**还是中间操作
     * 收集
     * collect ： 将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     *              Collectors 实用类提供了很多静态方法，可以方便地创建Collector实例。
     */
    @Test
    public void test2(){

        List<String> list = emps.stream()
                .map((e) -> e.getName())
                .collect(Collectors.toList());

        list.forEach(System.out::println);

        System.out.println("-------------------------");

        Set<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());

        set.forEach(System.out::println);
    }
    @Test
    public void test3(){

        //总数
        Long aLong = emps.stream()
                .collect(Collectors.counting());
        System.out.println(aLong);

        //平均数
        Double aDouble = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(aDouble);

        //总和
        Double aDouble1 = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(aDouble1);

        //最大值
        Optional<Employee> max = emps.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(max.get());

        //最小值
        Optional<Employee> min = emps.stream()
                .collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(min.get());

        //或者
        DoubleSummaryStatistics dss = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getSum());
        System.out.println(dss.getMin());
        System.out.println(dss.getMax());
        System.out.println(dss.getAverage());
    }

    //分组操作
    @Test
    public void test4(){

        Map<Employee.Status, List<Employee>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStauts));

        System.out.println(map);

        //多级分组
        Map<Employee.Status, Map<String, List<Employee>>> mapMap = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStauts, Collectors.groupingBy((e) -> {
                    if (e.getAge() <= 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));

        System.out.println(mapMap);
    }

    @Test
    public void test5(){

        String s = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",", "@@@", "@@@"));//连接

        System.out.println(s);
    }
}
