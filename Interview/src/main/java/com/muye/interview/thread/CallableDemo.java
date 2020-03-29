package com.muye.interview.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 * 多线程中，第三种获得多线程的方式
 * @author : gwh
 * @date : 2020-03-06 09:37
 **/
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //两个线程，一个是main线程，一个是aa  FutureTask 线程
        //FutureTask(Callable<v> callable)
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
         new Thread(futureTask,"aa").start();
        System.out.println(Thread.currentThread().getName()+"*********");
        int result01 = 100;
        //要求获得callable线程的计算结果，如果没有计算完成，就得去强求，会导致阻塞，值需要计算完成
        int result02 = futureTask.get();
        System.out.println("*******result:"+(result01+result02));
    }

}

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("************ com in callable ***********");
        return 1024;
    }
}
