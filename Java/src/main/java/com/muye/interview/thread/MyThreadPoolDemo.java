package com.muye.interview.thread;


import java.util.concurrent.*;

/**
 *
 *           末尾加s的辅助工具类
 * //        Array  Arrays
 * //        Collection  Collections
 * //        Executor Executors
 * //        List<String> list = Arrays.asList("a","b","c");
 *
 * Executors.newFixedThreadPool(5)  执行长期任务，性能好很多
 * Executors.newSingleThreadExecutor()  一个任务一个任务执行的场景
 * Executors.newCachedThreadPool()  执行很多短期异步的小程序或者负载较轻的服务器
 *
 * @author : gwh
 * @date : 2020-03-06 10:54
 **/
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //手动创建线程池
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        try {
            for (int i = 0; i < 10; i++) {
                executorPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ((ExecutorService) executorPool).shutdown();
        }

    }

    //jdk 自带线程池的实现方法
    public static void threadPoolInit(){
        //一池5线程;
        Executor executorPool = Executors.newFixedThreadPool(5);
        //一池1线程
//        Executor executorPool = Executors.newSingleThreadExecutor();
        //一池N线程
//        Executor executorPool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                executorPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ((ExecutorService) executorPool).shutdown();
        }
    }
}
