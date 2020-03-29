package com.muye.interview.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类线程不安全的问题
 * java.util.ConcurrentModificationException  并发修改异常
 *
 * @author : gwh
 * @date : 2020-02-29 13:36
 **/
public class ContainNoSafeDemo {
    public static void main(String[] args) {

        //方案1：线程不安全，add方法没有加锁
        List<String> list = new ArrayList<>();
        //方案2：线程安全，但并发性降低
        List<String> list1 = new Vector<>();
        //方案3：synchronizedList
        List<Object> list2 = Collections.synchronizedList(new ArrayList<>());
        List<String> list3 = new CopyOnWriteArrayList<>();

//        testList(list3);

        Set<String> set = new CopyOnWriteArraySet<>();
//        testSet(set);

//        Map<String, String> map = new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();
        testMap(map);

    }

    public static void testList(List<String> list) {
        for (int i = 1; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    public static void testSet(Set<String> set) {
        for (int i = 1; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    public static void testMap(Map<String, String> map) {
        for (int i = 1; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

}
