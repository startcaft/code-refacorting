package com.startcaft.www;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8 内置的四大核心函数式接口：
 *      Consumer<T> ：消费型接口
 *          void accept(T t);
 *
 *      Supplier<T> ：供给型接口
 *          T get();
 *
 *      Function<T,R> ：函数型接口
 *          R apply(T t);
 *
 *      Predicate<T> ：断言型接口
 *          boolean test(T);
 */
public class TestLambda4 {

    //Consumer<T> 消费型接口
    @Test
    public void test1(){
        happy(1000,(money) -> System.out.print("消费了 " + money + " 元钱"));
    }

    public void happy(double money, Consumer<Double> com){
        com.accept(money);
    }

    //Supplier<T> 供给型接口
    @Test
    public void test2(){
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        numList.forEach(System.out::println);
    }

    public List<Integer> getNumList(int count, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<count;i++){
            Integer integer = supplier.get();
            list.add(integer);
        }

        return list;
    }

    //Function<T,R> 函数型接口
    @Test
    public void test3(){
        String str = "abc";
        String s = strHandler(str, (x) -> x.toUpperCase());
        System.out.println(s);
    }
    public String strHandler(String str, Function<String,String> function){
        return function.apply(str);
    }

    //Predicate<T> 断言型接口
    @Test
    public void test4(){
        List<String> strings = Arrays.asList("hello", "startcaft", "lambda","www","cn");
        List<String> stringList = filterStr(strings, (str) -> str.length() > 3);
        stringList.forEach(System.out::println);
    }
    public List<String> filterStr(List<String> strings, Predicate<String> predicate){
        List<String> list = new ArrayList<>();
        for(String str : strings){
            if (predicate.test(str)){
                list.add(str);
            }
        }
        return list;
    }
}
