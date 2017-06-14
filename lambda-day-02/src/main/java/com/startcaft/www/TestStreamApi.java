package com.startcaft.www;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 一，什么是Stream？
 *
 * 流(Stream)是数据渠道，用于操作数据源(数组，集合等)所生成的元素序列。
 *
 *      "集合讲的是数据，流讲的是计算"
 *
 * 注意：
 *      1，Stream 自己不会存储元素。
 *      2，Stream 不会改变源对象。相反，它会返回一个持有结果的新的Stream。
 *      3，Stream 操作是延迟执行的。这意味着它们会等到需要结果的时候才执行。
 * --------------------------------------------------------------------
 *
 * 二，Stream 的三个操作步骤：
 *      1，创建Stream
 *      2，中间操作
 *      3，终止操作（终端操作）
 */
public class TestStreamApi {

    List<Employee> emps = Arrays.asList(
            new Employee("张三", 18, 9999.99, Employee.Status.BUSY),
            new Employee("李四", 38, 8888.88,Employee.Status.FREE),
            new Employee("王五", 42, 18888.88,Employee.Status.FREE),
            new Employee("赵六", 35, 12888.88, Employee.Status.VOCATION),
            new Employee("田七", 32, 10888.88,Employee.Status.VOCATION),
            new Employee("田七", 32, 10888.88,Employee.Status.VOCATION)
    );

    //创建Stream
    @Test
    public void test1(){

        //1，可以通过 Collection 系列集合提供的 stream() 或 parallelStream() 获取。
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //2，通过 Arrays 中的静态方法 stram() 获取一个数组流。
        Employee[] emps = new Employee[10];
        Stream<Employee> employeeStream = Arrays.stream(emps);

        //3，通过 Stream 类中的静态方法 of 获取。
        Stream<String> stringStream = Stream.of("aa","bb","cc");

        //4，创建无限流，指定一个种子，按照一个一元运算创建一个无限制的流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0,(x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);
        //stream4.forEach(System.out::println);//终止操作

        //生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);
    }

    //中间操作
    //多个中间操作可以连接起来形成一个流水线，而在终止操作的时候一次性全部处理，称为"惰性求值"。
    /**
     * 筛选与切片
     * filter ： 接收Lambda表达式，从流中排除掉某些元素
     * limit ： 截断流，使其元素不超过指定数量
     * skip(n) ： 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     * distinct ： 筛选。通过流生成元素的 hashCode() 和 equals() 去除重复元素
     */
    @Test
    public void test2(){//filter

        Stream<Employee> employeeStream = emps.stream()
                                            .filter((emp) -> emp.getAge() >= 35 );//中间操作是不会执行任何操作
                                                                                    //内部迭代，StreamAPI 完成。
        employeeStream.forEach(System.out::println);//终止操作
    }
    @Test
    public void test3(){//limit，短路，满足条件的数据获取到后就不再继续迭代。有时会提高效率
        emps.stream()
                .filter((e) -> {
                    System.out.println("短路");
                    return e.getSalary() > 5000;
                })
                .limit(2)
                .forEach(System.out::println);
    }
    @Test
    public void test4(){//skip
        emps.stream()
                .filter((e) -> e.getSalary() > 5000)
                .skip(2)
                .forEach(System.out::println);
    }
    @Test
    public void test5(){//distinct 根据 hashCode 和 equals 方法进行去重
        emps.stream()
                .filter((e) -> e.getSalary() > 5000)
                .distinct()
                .forEach(System.out::println);
    }

    //中间操作
    /**
     * 映射
     * map ： 接收Lambda表达式，将元素转换成其他形式或提取信息。接收一个函数作为参数。该函数会被应用到每一个元素上，并将其映射成一个新的元素。
     * flatMap ： 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
     */
    @Test
    public void test6(){

        List<String> list = Arrays.asList("aa","bb","cc","dd","ee");

        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);
        System.out.println("-------------------------");

        emps.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
        System.out.println("-------------------------");

        //流中包含流
        Stream<Stream<Character>> characterStream =  list.stream()
                .map(TestStreamApi::filterCharacter);//{{a,a},{b,b},{c,c}}

        characterStream.forEach((stream) -> stream.forEach(System.out::println));
        System.out.println("-------------------------");//{{a,a},{b,b},{c,c}} 最终把这3个流合并成一个流。{a,a,b,b,c,c,d,d,e,e}

        //流中包含流，麻烦，所以使用flatMap
        list.stream()
                .flatMap(TestStreamApi::filterCharacter)
                .forEach(System.out::println);
    }
    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for(Character ch : str.toCharArray()){
            list.add(ch);
        }
        return list.stream();
    }

    //中间操作
    /**
     * 排序
     * sorted()： 自然排序(Comparable，对象的Comparable方法排序)。
     * sorted(Comparator com) ： 定制排序。
     */
    @Test
    public void test7(){

        List<String> list = Arrays.asList("aa","bb","cc","dd","ee");

        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("-------------------------");

        emps.stream()
                .sorted((e1,e2) -> {
                    if(e1.getAge() .equals(e2.getAge())){
                        return e1.getName().compareTo(e2.getName());
                    }
                    else{
                        return Integer.compare(e1.getAge(),e2.getAge());
                    }
                })
                .forEach(System.out::println);
    }

    //终止操作
    /**
     * 查找与匹配
     * allMatch ： 检查是否匹配所有元素
     * anyMatch ： 检查是否至少匹配一个元素
     * nonMatch ： 检查是否没有匹配所有元素
     * findFirst ： 返回第一个元素
     * findAny ： 返回当前流中的任意元素
     * count ： 返回流中元素的总个数
     * max ： 返回流中最大值
     * min ： 返回流中最小值
     */
    @Test
    public void test8(){

        boolean b = emps.stream()
                .allMatch((e) -> e.getStauts().equals(Employee.Status.BUSY));
        System.out.println("是否都在忙碌:" + b);


        b = emps.stream()
                .anyMatch((e) -> e.getStauts().equals(Employee.Status.FREE));
        System.out.println("是否有人处于空闲状态：" + b);


        b = emps.stream()
                .noneMatch((e) -> e.getStauts().equals(Employee.Status.BUSY));
        System.out.println("所有人都在忙碌：" + b + "，至少有人不是在忙碌状态");


        //按照工资排序，获取第一个，有可能为空 Optional 容器是java8减少 空指针异常的发生
        Optional<Employee> op = emps.stream()
                .sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(op.get());


        //获取 空闲 状态的随意一个人
        Optional<Employee> op1 = emps.stream()
                .filter((e) -> e.getStauts().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(op1.get());


        //获取 忙碌 的总数
        long count = emps.stream()
                .filter((e) -> e.getStauts().equals(Employee.Status.BUSY))
                .count();
        System.out.println("忙碌的人有:" + count + "个");


        //获取年龄最小的那一个员工
        Optional<Employee> max = emps.stream()
                .max((e1, e2) -> -Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println(max.get());

        //获取工资最少的工资数
        Optional<Double> min = emps.stream()
                .map(Employee::getSalary)//提取工资为一个流
                .min(Double::compare);//获取最小的一个
        System.out.println(min.get());
    }
}
