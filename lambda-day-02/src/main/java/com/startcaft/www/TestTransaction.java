package com.startcaft.www;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by startcaft on 2017/6/15.
 */
public class TestTransaction {
    List<Transaction> transactions = null;

    @Before
    public void before(){
        Trader raoul = new Trader("Raoul","Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian,2011,300),
                new Transaction(raoul,2012,1000),
                new Transaction(raoul,2011,400),
                new Transaction(mario,2012,710),
                new Transaction(mario,2012,700),
                new Transaction(alan,2012,950)
        );
    }

    //1，找出2011年发生的所有交易，并按照交易额排序（从高到底）
    @Test
    public void test(){

        transactions.stream()
                .filter((t) -> t.getYear() == 2011)
                .sorted((t1,t2) -> Integer.compare(t1.getValue(),t2.getValue()))
                .forEach(System.out::println);
    }

    //2，交易员都在哪些不同的城市工作
    @Test
    public void test2(){

        transactions.stream()
                .map((t) -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }

    //3，查找所有来自于剑桥的交易，并按照姓名排序
    @Test
    public void test3(){

        transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted((t1,t2) -> t1.getName().compareTo(t2.getName()))
                .distinct()
                .forEach(System.out::println);
    }

    //4，返回所有交易员的姓名，按字母排序
    @Test
    public void test4(){

        transactions.stream()
                .map((t) -> t.getTrader().getName())
                .sorted()
                .forEach(System.out::println);

        System.out.println("--------------------------------------");

        String reduce = transactions.stream()
                .map((t) -> t.getTrader().getName())
                .sorted()
                .reduce("", String::concat);
        System.out.println(reduce);
    }

    //5，看看有没有交易员在米兰工作
    @Test
    public void test5(){

        boolean b = transactions.stream()
                .anyMatch((t) -> t.getTrader().getCity().equalsIgnoreCase("milan"));

        System.out.println(b);
    }

    //6，打印生活在剑桥的交易员的所有交易额
    @Test
    public void test6(){

        Optional<Integer> integerOptional = transactions.stream()
                .filter((t) -> t.getTrader().getCity().equalsIgnoreCase("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);

        System.out.println(integerOptional.get());
    }

    //7，所有交易额中，最高的
    @Test
    public void test7(){

        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compare);
        System.out.println(max.get());
    }

    //8，找打交易额最小的交易
    @Test
    public void test8(){

        Optional<Transaction> min = transactions.stream()
                .min((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()));
        System.out.println(min.get());
    }
}
