package com.muye.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @Desc: 线程池的创建
 *
 * @author : gwh
 * @date : 2020-06-29 15:05
 **/
public class ArrayListTest {

  private static List<String> list = new ArrayList<String>();

    public static void main(String[] args) {
        list.add("1");
        list.add("2");
        list.add("3");
        final Iterable<String> it = new ArrayList<>();
        //创建线程池：方式一,可控制线程最大并发数
        Executors.newFixedThreadPool(10);

        //创建线程池：方式二，创建一个可缓存的线程池
        Executors.newCachedThreadPool();

        //创建线程池：方式三，创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，
        // 保证所有任务按照（FIFO、FILO，优先级）执行
        Executors.newSingleThreadExecutor();

        //创建线程池：方式四，创建一个定长的线程，支持定时及周期性任务执行
        Executors.newScheduledThreadPool(100);

    }



}
