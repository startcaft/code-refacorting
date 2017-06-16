package com.startcaft.www;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Optional<T> 是java8中的一个容器类，位于 java.util 包下，它代表一个值或对象存在或不存在。
 * 原来我们使用 null 来表示一个值或对象不存在，现在 Optional 可以更好的表达这个概念，并且可以尽可能的避免空指针异常。
 * <p>
 * 常用方法：
 * Optional.of(T t) ： 创建一个 Optional 实例
 * Optional.empty() ： 创建一个空的 Optional 实例
 * Optional.ofNullable(T t) ： 若 t 不为null，则创建 Optional 实例，否则创建空实例。
 * get() ： 获取对象或值
 * isPresent() ： 判断是否包含值
 * orElse(T t) : 如果调用对象包含值，则返回该值，否则返回 t
 * orElseGet(Supplier s) ： 如果调用对象包含值，返回该值，否则返回 s 获取的值
 * map(Function f) ： 如果有值对其处理，并返回处理后的 Optional，否则返回 Optional.empty()
 * flatMap(Function mapper) ： 与 map 类似，要求返回值必须是 Optional
 */
public class TestOptional {

    //Optional.of(T t) ： 创建一个 Optional 实例
    @Test(expected=NullPointerException.class)
    public void test() {

        Optional<Employee> op = Optional.of(new Employee());
        Employee emp = op.get();
        System.out.println(emp);

        /*注意：实际开发者中 new Employee() 大都是由别的地方传递过来的，那么就有可能为 null 了*/
        op = Optional.of(null);//异常发生处，不能使用 null 构建
        emp = op.get();
        System.out.println(emp);
    }

    //Optional.empty() ： 创建一个空的 Optional 实例
    @Test(expected = NoSuchElementException.class)
    public void test2(){

        Optional<Employee> op = Optional.empty();
        System.out.println(op.get());//构建的空的Optional。get值肯定发生异常
    }

    //Optional.ofNullable(T t) ： 若 t 不为null，则创建 Optional 实例，否则创建空实例。
    //ofNullable是 of 和 empty 的中和。
    @Test(expected = NoSuchElementException.class)
    public void test3(){

        Optional<Employee> op = Optional.ofNullable(null);
        System.out.println(op.get());
    }

    //isPresent() ： 判断是否包含值
    @Test
    public void test4(){

        Optional<Employee> op = Optional.ofNullable(null);

        if(op.isPresent()){
            System.out.println(op.get());
        }

        op = Optional.ofNullable(new Employee());

        if(op.isPresent()){
            System.out.println(op.get());
        }
    }

    //orElse(T t) : 如果调用对象包含值，则返回该值，否则返回 t !!!!!!!!!!!!!
    @Test
    public void test5(){

        Optional<Employee> op = Optional.ofNullable(null);

        Employee employee = op.orElse(new Employee("张三", 23, 9999));//Optional 有值就返回封装的值，没有则返回手动构建的值

        System.out.println(employee);
    }

    //orElseGet(Supplier s) ： 如果调用对象包含值，返回该值，否则返回 s 获取的值 !!!!!!!!!!!!!
    @Test
    public void test6(){

        Optional<Employee> op = Optional.ofNullable(null);

        Employee employee = op.orElseGet(() -> new Employee());

        System.out.println(employee);
    }

    @Test
    public void test7(){

        Optional<Employee> op = Optional.ofNullable(new Employee("张三", 23, 9999));

        Optional<String> stringOptional = op.map(Employee::getName);

        System.out.println(stringOptional.get());
    }

    @Test
    public void test8(){

        Optional<Employee> op = Optional.ofNullable(new Employee("张三", 23, 9999));

        Optional<String> stringOptional = op.flatMap((e) -> Optional.of(e.getName()));

        System.out.println(stringOptional.get());
    }
}
