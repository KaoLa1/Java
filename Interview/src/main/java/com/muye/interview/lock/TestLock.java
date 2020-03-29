package com.muye.interview.lock;



/**
 * 可重入锁
 *
 * @author : gwh
 * @date : 2020-03-02 16:23
 **/
public class TestLock {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendMsg();
        },"t1").start();

        new Thread(()->{
            phone.sendMsg();
        },"t2").start();
    }
}


class Phone {

    public synchronized void sendMsg() {
        System.out.println(Thread.currentThread().getId() + "\t  sendMsg");
        sendMail();
    }

    public synchronized void sendMail() {
        System.out.println(Thread.currentThread().getId() + "\t ######### sendMail");
    }
}
