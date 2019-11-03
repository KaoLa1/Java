package com.java8;

/**
 * <h3>Java8</h3>
 * <p>过滤年龄</p>
 *
 * @author : gwh
 * @date : 2019-11-03 08:39
 **/
public class FilterEmployeeForAge implements MyPredicate {

    @Override
    public boolean test(Employee employee) {
        return employee.getAge() > 35;
    }
}
