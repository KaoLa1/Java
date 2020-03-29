package com.muye.interview.lock;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : gwh
 * @date : 2020-03-05 14:25
 **/
public class SyncAndReentrantLock {
    public static void main(String[] args) {
        Share share = new Share();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                share.print5();
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                share.print10();
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                share.print15();
            }
        },"CC").start();
    }
}

class Share {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            //判断
            while (number != 1) {
                condition1.await();
            }
            //干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+i);
            }
            //通知：修改标志位，准备唤醒下一个线程
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            //判断
            while (number != 2) {
                condition2.await();
            }
            //干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+i);
            }
            //通知：修改标志位，准备唤醒下一个线程
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            //判断
            while (number != 3) {
                condition3.await();
            }
            //干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+i);
            }
            //通知：修改标志位，准备唤醒下一个线程
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
