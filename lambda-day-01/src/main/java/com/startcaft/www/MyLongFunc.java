package com.startcaft.www;

/**
 * Created by startcaft on 2017/6/12.
 */
@FunctionalInterface
public interface MyLongFunc<T,R> {

    public R getValue(T t1,T t2);
}
