package com.muye.interview.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多线程同时读一个资源类没有问题，为了满足并发量，读取共享资源应该可以同时进行
 * 但是如果一个线程去写资源，就不应该有其他线程去写资源
 * 小总结：
 * 读读可以共存
 * 读写不能共存
 * 写写不能共存
 *
 * @author : gwh
 * @date : 2020-03-03 12:01
 **/
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i <= 5; i++) {
            final int tmpInt = i;
            new Thread(() -> {
                myCache.put("" + tmpInt, tmpInt);
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i <= 5; i++) {
            final int tmpInt = i;
            new Thread(() -> {
                myCache.get("" + tmpInt);
            }, String.valueOf(i)).start();
        }

    }
}

class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入--" + key);
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成：");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取--" + key);
            TimeUnit.MILLISECONDS.sleep(300);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }
}
