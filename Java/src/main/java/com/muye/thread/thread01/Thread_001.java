package com.muye.thread.thread01;

/**
 * @author : gwh
 * @Desc:
 * @date : 2020-06-22 20:43
 **/
public class Thread_001 {

    Demo1 demo1 = new Demo1();

    Thread t1 = new Thread(demo1);
    Thread t2 = new Thread(demo1);
    Thread t3 = new Thread(demo1);


}


class Demo1 implements Runnable {

    private String name;



    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(name + "++++++++");
        }
    }

}


