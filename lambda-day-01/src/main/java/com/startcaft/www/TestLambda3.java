package com.startcaft.www;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Lambda 表达式练习
 */
public class TestLambda3 {

    List<Employee> emps = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 8888.88),
            new Employee("王五", 42, 18888.88),
            new Employee("赵六", 35, 12888.88),
            new Employee("田七", 32, 10888.88)
    );

    @Test
    public void test1(){
        Collections.sort(emps,(e1,e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }
            else{
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });

        emps.forEach(System.out::println);
    }

    @Test
    public void test2(){
        String result = strHandler("dsadasdadasd ",(str) -> {
            return str.toUpperCase();
        });

        System.out.println(result);
    }

    @Test
    public void test3(){
        op(100L,200L,(x,y) -> x + y);
    }

    //需求：用于处理字符串的方法
    public String strHandler(String str,MyStringFunc strFunc){
        return strFunc.getValue(str);
    }

    //需求：对于两个 long 型数据进行处理
    public void op(Long l1,Long l2,MyLongFunc<Long,Long> mlf){
        System.out.println(mlf.getValue(l1,l2));
    }
}
