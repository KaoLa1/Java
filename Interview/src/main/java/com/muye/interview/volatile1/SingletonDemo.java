package com.muye.interview.volatile1;

/**
 * @author : gwh
 * @date : 2020-02-28 09:32
 **/
public class SingletonDemo {

    private static SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法,SingletonDemo()");
    }

    public static   SingletonDemo getInstance() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }

    public static void main(String[] args) {
        //单线程操作
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());

        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
