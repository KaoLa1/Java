package com.muye.interview.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : gwh
 * @date : 2020-02-28 13:56
 **/
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        boolean b = atomicInteger.compareAndSet(5,2019 );
        System.out.println(b+" "+atomicInteger.get());
        atomicInteger.getAndIncrement();
    }
}
