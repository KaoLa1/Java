package com.muye.interview.lock;

import java.util.Timer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author : gwh
 * @date : 2020-03-04 17:32
 **/
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                blockingQueue.put("a");
                System.out.println(Thread.currentThread().getName()+"\t put a ....");

                blockingQueue.put("b");
                System.out.println(Thread.currentThread().getName()+"\t put b ....");

                blockingQueue.put("c");
                System.out.println(Thread.currentThread().getName()+"\t put c ....");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();


        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t"+  blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t"+  blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t"+  blockingQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
    }
}
