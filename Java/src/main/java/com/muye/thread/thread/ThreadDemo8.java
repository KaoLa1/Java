package com.muye.thread.thread;

/**
 * 当同步函数被static修饰后，使用的锁是什么呢？
 * <p>
 * static方法中是不可以出现this的。
 * 通过运行示例也验证了这一点。
 * <p>
 * <p>
 * static是随着类的加载而加载，这时还没有建立该类的对象。
 * 只有类被加载进了内存，这时只有一个对象存在就是该类所对应的字节码文件对象。
 * <p>
 * 静态同步方法锁使用的锁是：该方法所属类的字节码文件对象。
 * 就是  本类名.class
 */
class ThreadDemo8 {
    public static void main(String[] args) {
        SaleTicket6 st = new SaleTicket6();
        Thread t1 = new Thread(st);
        Thread t2 = new Thread(st);
        t1.start();
        //让主线程稍微停一下，让t1先去执行同步代码块。
        //然后主线程醒了以后，在将标记进行切换，让t2去执行同步函数。
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        st.b = false;
        t2.start();


    }
}

class SaleTicket6 implements Runnable {
    private static int tick = 300;

    boolean b = true;

    @Override
    public void run() {
        if (b) {
            while (true) {
                synchronized (SaleTicket.class) {
                    if (tick > 0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                        }
                        System.out.println(Thread.currentThread().getName() + ".....obj...." + tick--);
                    }
                }
            }
        } else {
            while (true) {
                show();
            }
        }

    }

    public synchronized static void show() {
        if (tick > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ".....show...." + tick--);
        }
    }
}