package com.startcaft.www;

/**
 * Created by startcaft on 2017/6/9.
 */
public class FilterEmployeeByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee t) {
        return t.getAge() >= 35;
    }
}
