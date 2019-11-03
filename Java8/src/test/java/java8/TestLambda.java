package java8;

import com.java8.Employee;
import com.java8.FilterEmployeeForAge;
import com.java8.FilterEmployeeSalary;
import com.java8.MyPredicate;
import org.junit.Test;

import java.util.*;

/**
 * <h3>Java8</h3>
 * <p>Lambda表达式测试</p>
 *
 * @author : gwh
 * @date : 2019-11-02 11:44
 **/
public class TestLambda {
    //匿名内部类(针对简单的接口，不需要新建一个类去实现接口)
    @Test
    public void test1() {
        Comparator<String> com = new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };

        TreeSet<String> ts = new TreeSet<>(com);

        TreeSet<String> ts2 = new TreeSet<>(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }

        });
    }

    //Lambda表达式
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> treeSet = new TreeSet<>(com);
    }

    //查询年龄35的员工信息
    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    public List<Employee> filterEmployeeSalary(List<Employee> list) {
        List<Employee> list1 = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getAge() > 35) {
                list1.add(employee);
            }
        }
        return list1;
    }

    @Test
    public void test3() {
        List<Employee> list = filterEmployeeSalary(emps);
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    //优化方式一：策略设计模式
    public List<Employee> filterEmployee(List<Employee> employees, MyPredicate mp) {
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employees) {
            if (mp.test(employee)) {
                list.add(employee);
            }
        }
        return list;
    }


    @Test
    public void test4() {
        List<Employee> list = filterEmployee(emps, new FilterEmployeeForAge());
        for (Employee employee : list) {
            System.out.println(employee);
        }
        System.out.println("--------------------------");
        List<Employee> list1 = filterEmployee(emps, new FilterEmployeeSalary());
        for (Employee employee : list1) {
            System.out.println(employee);
        }
    }

    //优化方式2：匿名内部类
    @Test
    public void test5() {
        List<Employee> list = filterEmployee(emps, new MyPredicate() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() < 5000;
            }
        });
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    //优化方式3：Lambda表达式
    @Test
    public void test6() {
        List<Employee> list = filterEmployee(emps, (e) -> e.getSalary() < 5000);
        list.forEach(System.out::println);
    }

    //优化方式4：stream API
    @Test
    public void test7() {
        emps.stream()
                .filter((e) -> e.getSalary() > 5000)
                .limit(2).forEach(System.out::println);
        System.out.println("------------");
        emps.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }
}
