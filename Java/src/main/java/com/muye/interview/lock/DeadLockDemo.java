package com.muye.interview.lock;


import java.util.concurrent.TimeUnit;

/**
 * @author : gwh
 * @date : 2020-03-09 10:30
 **/
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA,lockB),"AAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"BBB").start();
    }
}

class HoldLockThread implements Runnable {

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA,String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
          synchronized (lockA){
              System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockA+"\t 尝试持有"+lockB);
              try {
                  TimeUnit.SECONDS.sleep(3);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
          synchronized (lockB){
              System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockB+"\t 尝试持有"+lockA);
          }
    }
}
