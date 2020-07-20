package com.muye.java8.lambda;

import com.muye.java8.service.MyFun;
import entity.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * <h3>Java8</h3>
 * <p>lambda基础语法</p>
 *
 * @author : gwh
 * @date : 2019-11-03 13:24
 **/
public class TestLambda1 {
    @Test
    public void test1() {
        Runnable runnable = new Runnable() {
            int num = 0; //jdk 1.7 前，必须是 final

            @Override
            public void run() {
                System.out.println("Hello world " + num);
            }
        };
        runnable.run();
        System.out.println("----------------");

        //lambda 表达式
        Runnable r = () -> System.out.println("Hello lambda");
        r.run();
    }

    @Test
    public void test2() {
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("测试有一个参数的");
    }

    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }

    @Test
    public void test4() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );


    @Test //需求：获取公司中年龄小于 35 的员工信息
    public void test5() {
        emps.stream()
                .filter((e) -> e.getAge() <= 35)
                .forEach(System.out::println);

        System.out.println("----------------------------------------------");

        emps.stream()
                .map(Employee::getName)
                .limit(3)
                .sorted()
                .forEach(System.out::println);
    }


    @Test //需求：对一个数进行运算
    public void test6() {
        Integer num = operation(100, (x) -> x * x);
        System.out.println(num);

        System.out.println(operation(200, (y) -> y + 200));
    }

    public Integer operation(Integer num, MyFun mf) {
        return mf.getValue(num);
    }


}
