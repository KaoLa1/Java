package com.muye.interview.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author : gwh
 * @date : 2020-03-03 17:36
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{
            System.out.println("召唤神龙");
        });

        for (int i = 1; i <= 7; i++) {
            final int tmpInt = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 收集到第："+tmpInt+"龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
