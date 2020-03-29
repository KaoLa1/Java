package com.muye.interview.volatile1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 验证volatile的可见性 、不保证原子性及解决方案
 *
 * @author : gwh
 * @date : 2020-02-27 11:21
 **/


public class VolatileDemo {
    public static void main(String[] args) {
        //资源类（线程操作资源类）
        MyData myData = new MyData();

//        notAutomicForVolatole(myData);

        seeOkByVolatile();
    }

    /**
     * 验证volatile不保证原子性以及解决方案
     *
     * @param myData
     */
    public static void notAutomicForVolatole(MyData myData) {
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlus();
//                    myData.addMyAutomic();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t finally value " + myData.num);
        System.out.println(Thread.currentThread().getName() + "\t atomicInteger finally value " + myData.atomicInteger);
    }

    /**
     * volatile可以保证可见性，及时通知其他线程，主屋里内存的值已经被修改
     */
    public static void seeOkByVolatile() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo();
            System.out.println(Thread.currentThread().getName() + "\t updated over" + myData.num);
        }, "AAA").start();

        while (myData.num == 0) {
            //如果访问的资源（num）没有加volatile,此处会一直等待
        }
        System.out.println(Thread.currentThread().getName() + "\t mission over " + myData.num);
    }
}

class MyData {

    volatile int num = 0;

    public void addTo() {
        this.num = 60;
    }

    public void addPlus() {
        num++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addMyAutomic() {
        atomicInteger.getAndIncrement();
    }
}
