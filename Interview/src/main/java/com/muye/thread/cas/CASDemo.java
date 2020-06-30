package com.muye.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gwh
 **/
public class CASDemo {
    public static void main(String[] args) {
        /**
         *  AtomicInteger 源代码 getAndIncrement()方法   UnSafe类是什么?
         */
        AtomicInteger atomicInteger = new AtomicInteger(5);
        // 比较并交换
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t current" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2014) + "\t current" + atomicInteger.get());
    }
}