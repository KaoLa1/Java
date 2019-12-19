package com.muye.exercise.java_01_thread.thread;

/**
 * 演示同步的另一个弊端 ：死锁。
 * <p>
 * 死锁通常发生在同步嵌套的嵌套。
 * <p>
 * <p>
 * 看懂即可。
 */
class ThreadDemo9 {
    public static void main(String[] args) {
        SaleTicket7 st = new SaleTicket7();
        Thread t1 = new Thread(st);
        Thread t2 = new Thread(st);
        t1.start();
        //让主线程稍微停一下，让t1先去执行同步代码块。
        //然后主线程醒了以后，在将标记进行切换，让t2去执行同步函数。
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        st.b = false;
        t2.start();
    }
}

class SaleTicket7 implements Runnable {
    private int tick = 500;
    private Object obj = new Object();


    boolean b = true;

    @Override
    public void run() {
        if (b) {
            while (true) {
                synchronized (obj) {
                    func();
                }
            }
        } else {
            while (true) {
                show();
            }
        }
    }

    public synchronized void show() {
        synchronized (obj) {
            if (tick > 0) {
                //try{Thread.sleep(10);}catch(InterruptedException e){}
                System.out.println(Thread.currentThread().getName() + ".....show...." + tick--);
            }
        }
    }

    public synchronized void func() {
        if (tick > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ".....func...." + tick--);
        }
    }
}



