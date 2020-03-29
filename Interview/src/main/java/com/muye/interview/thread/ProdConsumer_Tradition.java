package com.muye.interview.thread;

import javafx.scene.control.IndexRange;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减1，进行5轮
 * @author : gwh
 * @date : 2020-03-05 10:49
 **/
public class ProdConsumer_Tradition {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}

class ShareData{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (number!=0){
                //等待，不能生产
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t ..."+number);
            //通知唤醒
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number==0){
                //等待，不能生产
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t ..."+number);
            //通知唤醒
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}
