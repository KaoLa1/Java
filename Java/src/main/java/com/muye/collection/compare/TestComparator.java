package com.muye.collection.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Desc:
 *
 * @author : gwh
 * @date : 2020-07-19 10:08
 **/
public class TestComparator {
    public static void main(String[] args) {
        Student student1 = new Student("zhangsan", 1, 80);
        Student student2 = new Student("lisi", 3, 90);
        Student student3 = new Student("wangwu", 2, 100);
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        // 这时候如果直接  Collections.sort(list) 会由于Student没有默认的自然排序，编译不过。
        Collections.sort(list, new AgeComparator());
        list.stream().forEach(n -> System.out.println(n.toString()));
        System.out.println("\n-------------------");
        Collections.sort(list, new NameComparator());
        list.stream().forEach(n -> System.out.println(n.toString()));
    }
}
