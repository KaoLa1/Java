package com.muye.interview.volatile1;

/**
 * @author : gwh
 * @date : 2020-02-28 10:03
 **/
public class DCLSingleton {

    private static volatile DCLSingleton instance = null;

    private DCLSingleton(){
        System.out.println(Thread.currentThread().getName()+"/t 构造方法DCLSingleton( )");
    }


    /**
     * DCL (Double Check Lock,双端检锁机制) 不一定线程安全，原因是指令重排序的存在
     * @return
     */
    public static  DCLSingleton getInstance(){
        if(instance == null){
            synchronized (DCLSingleton.class){
                if(instance == null){
                instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        //单线程操作
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());

        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                DCLSingleton.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
