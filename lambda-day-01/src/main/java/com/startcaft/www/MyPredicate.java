package com.startcaft.www;

/**
 * Created by startcaft on 2017/6/9.
 */
@FunctionalInterface
public interface MyPredicate<T> {

    boolean test(T t);

    //boolean test2(T t);

}
