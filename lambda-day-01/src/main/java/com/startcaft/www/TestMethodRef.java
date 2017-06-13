package com.startcaft.www;

import com.sun.org.apache.bcel.internal.generic.BIPUSH;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 一，方法引用 ：若Lambda 体中的内容已有即有实现了，我们可以使用"方法引用"
 *      (可以理解为方法引用是 Lambda 表达式的另外一种表现形式)
 *
 * 主要有三种语法格式：
 *      [1]对象::实例方法签名
 *      [2]类::静态方法签名
 *      [3]类::实例方法签名
 *
 * 注意：
 *      1，Lambda 体中的参数列表与返回值类型，必须要与函数式接口中抽象方法的函数列表和返回值类型一致
 *      2，若 Lambda 参数列表中的第一个参数是 实例方法的调用者，而第二个参数是实例方法的参数时，就可以使用 ClassName::method
 * ------------------------------------------------------------------------------------------------------------------------------
 *
 * 二，构造器引用
 *
 * 格式：
 *      ClassName::new;
 *
 * 注意：
 *      需要调用的构造器的参数列表 必须要与函数式接口中抽象方法的参数列表一致
 * ------------------------------------------------------------------------------------------------------------------------------
 *
 * 三，数组引用
 *
 * 格式：
 *      Type[]::new;
 */
public class TestMethodRef {

    //数组引用
    @Test
    public void test6(){

        Function<Integer,String[]> function = (x) -> new String[x];
        String[] strs = function.apply(10);
        System.out.println(strs.length);


        Function<Integer,String[]> function1 = String[]::new;
        String[] strs2 = function.apply(20);
        System.out.println(strs2.length);
    }

    //构造器引用
    @Test
    public void test5(){

        Supplier<Employee> supplier = () -> new Employee("张三");
        Employee emp = supplier.get();
        System.out.println(emp);

        //构造器引用方式
        Supplier<Employee> supplier1 = Employee::new;//根据抽象方法中的参数列表自动匹配对应的构造器
        Employee emp1 = supplier1.get();
        System.out.println(emp1);

        Function<String,Employee> function = (x) -> new Employee(x);
        Employee emp2 = function.apply("李四");
        System.out.println(emp2);

        Function<String,Employee> function1 = Employee::new;
        Employee emp3 = function1.apply("王五");
        System.out.println(emp3);
    }

    //对象::实例方法签名
    @Test
    public void test(){

        PrintStream ps1 = System.out;
        Consumer<String> con = (x) -> ps1.println(x);//正常的Lambda表达式
        con.accept("123");

        //需要注意的是【需要实现的接口中的抽象方法的参数列表和返回值类型 必须与当前调用的方法的参数列表和返回值类型一致】
        PrintStream ps = System.out;
        Consumer<String> con1 = ps::println;//对象::实例方法签名
        con1.accept("456");

        Consumer<String> con2 = System.out::println;
        con2.accept("789");
    }

    @Test
    public void test2(){

        Employee emp = new Employee("zhangsan",25,8000);
        Supplier<String> supplier = () -> emp.getName();//正常的Lambda表达式
        String str = supplier.get();
        System.out.println(str);


        Supplier<Integer> supplier1 = emp::getAge;//对象::实例方法签名 Lambda另一种表现形式
        Integer num = supplier1.get();
        System.out.println(num);
    }

    //类::静态方法签名
    @Test
    public void test3(){

        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);

        Comparator<Integer> comparator1 = Integer::compare;

    }

    //类::实例方法签名
    @Test
    public void test4(){

        BiPredicate<String,String> predicate = (x,y) -> x.equals(y);

        //需要注意的是【如果第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，就可以使用 类::实例方法签名 的语法格式】
        BiPredicate<String,String> predicate1 = String::equals;
    }
}
