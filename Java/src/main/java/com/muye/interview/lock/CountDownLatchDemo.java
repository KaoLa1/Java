package com.muye.interview.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author : gwh
 * @date : 2020-03-03 15:37
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国，被灭");
                countDownLatch.countDown();
            },CountryEnum.forEach_Enum(i).getCountry()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t ********秦统一天下");
    }

    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 上完自习，离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t ********班长最后关门走人");
    }
}
