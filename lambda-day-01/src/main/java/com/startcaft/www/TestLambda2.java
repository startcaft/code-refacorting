package com.startcaft.www;

import org.junit.Test;
import java.util.*;
import java.util.function.Consumer;

/**
 * 一，Lambda 表达式的基础语法，java8中引入了一个新的 操作符 "->" ，该操作符成为 箭头操作符 或 Lambda操作符。
 *          箭头操作符将 Lambda 表达式分成两个部分：
 *              左侧：Lambda 表达式的参数列表
 *              右侧：Lambda 表达式中所需执行的功能，即 Lambda 体。
 *
 * 语法格式一：无参数，无返回值
 *      () -> System.out.println("Hello Lambda!");
 *
 * 语法格式二：一个参数，无返回值
 *      (param) -> System.out.println(param);
 *
 * 语法格式三：若只有一个参数，那么参数的小括号可以省略不写
 *      param -> System.out.println(param);
 *
 * 语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
 *      Comparator<Integer> comparator = (x,y) -> {
 *           System.out.println("函数式接口");
 *           return Integer.compare(x,y);
 *      };
 *
 * 语法格式五：有两个以上的参数，有返回值，并且 Lambda 体中只有一条语句，则大括号{}和return都可以省略不写
 *
 * 语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为 JVM 编译器可以通过上下文推导出，数据类型，即"类型推导"
 *      (Integer x,Integer y) -> Integer.compare(x,y);
 *
 * 归纳总结：
 *      上联：左右遇一括号省
 *      下联：左侧推断类型省
 *      横批：能省则省
 *
 *
 * Lambda 表达式 需要“函数式接口”
 *      若接口中只有一个抽象方法时，就称为函数式接口。
 *      可以使用 @FunctionalInterface 修饰接口，可以检查该接口是否是函数式接口。如果定义了多个抽象方法，则编译报错。
 */
public class TestLambda2 {

    @Test
    public void test1(){
        int num = 23;//jdk 1.7 以前，该变量必须是final的。1.8之后默认会加上final修饰。

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World " + num);
            }
        };
        runnable.run();

        System.out.println("-------------------");

        runnable = () -> System.out.println("Hello Lambda" + num);
        runnable.run();
    }

    @Test
    public void test2(){
        Consumer<String> com = (x) -> System.out.println(x);
        com.accept("Lambda真流弊啊");


        com = x ->System.out.println(x);
        com.accept("Lambda真流弊啊222222");
    }

    @Test
    public void test3(){
        Comparator<Integer> comparator = (x,y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
    }

    @Test
    public void test4(){
//        Comparator<Integer> comparator = (Integer x,Integer y) -> Integer.compare(x,y);
        Comparator<Integer> comparator = ( x, y) -> Integer.compare(x,y);
    }

    @Test
    public void test5(){

        String[] agrs = {"a","b","c"};//这就是类型推导

        /*以下的语句就无法推导出数据类型了
        String[] args1 ;
        args1 = {"a","b","c"};
        */

        List<String> list = new ArrayList<>();//这也是类型推导

        show(new HashMap<>());//jdk 1.7 中编译出错。jdk 1.8 类型推导也跟着升级了
    }

    public void show(Map<String,Integer> map){

    }

    //需求：对一个数进行运算。
    @Test
    public void testOperation(){
        Integer result = operation(100,(x) -> x * x);
        System.out.println(result);

        result = operation(200,(x) -> x + 200);
        System.out.println(result);
    }

    public Integer operation(Integer number,MyFunction mf){
        return mf.getValue(number);
    }
}
