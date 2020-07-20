package com.muye.collection.queue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : gwh
 * @Desc:
 * @date : 2020-07-18 10:17
 **/
public class QueueDemo {
    Logger logger = LoggerFactory.getLogger(Queue.class);

    @Test
    public void test() {
        Queue<String> queue = new LinkedList<>();
        queue.offer("a");
        queue.offer("b");
        System.out.println(queue.toString());
        logger.info("poll=" + queue.poll()); //返回第一个元素，并在队列中删除
        System.out.println(queue.toString());
        queue.offer("a");
        System.out.println(queue.toString());
        logger.info("poll=" + queue.element()); //返回第一个元素
        System.out.println(queue.toString());
        logger.info("poll=" + queue.peek()); //返回第一个元素 
        System.out.println(queue.toString());
    }
}
