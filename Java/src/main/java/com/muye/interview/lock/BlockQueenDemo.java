package com.muye.interview.lock;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * ArrayBlockingQueue:是一个基于数据结构的有界阻塞队列，此队列按FIFO（先进先出）的原则对元素进行排序
 * LinkedBlockingQueue:一个基于链表结构的阻塞队列，此队列按照FIFO（先进先出）的原则对元素进行排序
 * SynchronousQueue:一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直会处于阻塞状态，吞吐量通常要高
 *
 * 1 队列
 * 2 阻塞队列
 *
 *  2.1 阻塞队列有没有好的一面
 *  2.2 不得不阻塞，你如何管理
 *
 * MQ 消息中间件的核心底层原理 阻塞队列
 *
 * @author : gwh
 * @date : 2020-03-04 14:29
 **/
public class BlockQueenDemo {
    public static void main(String[] args)  throws Exception{
//      List list = new ArrayList();
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        //检查队列是否为空
        System.out.println(blockingQueue.element());

            //队列满的时候添加，会报queue异常
//        System.out.println(blockingQueue.add("x"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

           //队列空的时候，移除会报NoSuchElementException
//        System.out.println(blockingQueue.remove());


        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x"));

        //探测顶端元素
        System.out.println(blockingQueue.peek());

            blockingQueue.put("a");
            blockingQueue.put("b");
            blockingQueue.put("c");
//            blockingQueue.offer("x");

            blockingQueue.take();
            blockingQueue.take();
            blockingQueue.take();
            blockingQueue.take();

        System.out.println(blockingQueue.offer("a",2L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b",2L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c",2L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("x",2L,TimeUnit.SECONDS));


   }
}
