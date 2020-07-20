package com.muye.class1.hash1equals;

import entity.Person;

import java.util.HashSet;

public class DemoConflictTest {

    public static void main(String[] args) {

        // 新建Person对象，
        Person p1 = new Person("eee", 100);
        Person p2 = new Person("eee", 100);
        Person p3 = new Person("aaa", 200);
        // 新建HashSet对象
        HashSet<Person> set = new HashSet<>();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        // 比较p1 和 p2， 并打印它们的hashCode()
        System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        // 打印set
        System.out.printf("set:%s\n",set);
    }

}
