package com.muye.interview.thread;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : gwh
 * @date : 2020-03-05 16:27
 **/
public class ProdConsumer_BlockQueue {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(12));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 线程成产启动");
            try {
                myResource.myProd();
                System.out.println();
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"MyProd").start();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t 线程消费启动");
                myResource.myConsumer();
                System.out.println();
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"myConsumer").start();

        System.out.println();
        System.out.println();

        System.out.println("老板叫停。。");
        myResource.stop();
    }
}

class MyResource{
    /**
     * 默认开启，进行生产加消费
     */
    private volatile boolean Flag = true;
    private AtomicInteger aomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(BlockingQueue.class.getName());
    }

    public void myProd() throws InterruptedException {
        String data;
        boolean retValue;
        while(Flag){
             data = aomicInteger.incrementAndGet()+"";
             retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
             if(retValue){
                 System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
             }else{
                 System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失败");
             }
             TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"大老板叫停了");
    }

    public void myConsumer() throws InterruptedException {
        String result = null;
        while (Flag){
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(null==result||result.equalsIgnoreCase("")){
                Flag = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过两秒钟没有收到蛋糕，消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"成功");
        }

    }

    public void stop(){
        Flag = false;
    }
}

