package com.java8;

/**
 * <h3>Java8</h3>
 * <p>过滤工资</p>
 *
 * @author : gwh
 * @date : 2019-11-03 11:11
 **/
public class FilterEmployeeSalary implements MyPredicate{
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary()<3500;
    }
}
