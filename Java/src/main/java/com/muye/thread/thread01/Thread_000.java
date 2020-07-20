package com.muye.thread.thread01;

/**
 * @author : gwh
 * @Desc: 创建线程 -- 继承Thread类
 * @date : 2020-06-22 20:25
 **/
public class Thread_000 {

    public static void main(String[] args) {
        Demo d1 = new Demo("d1");
        Demo d2 = new Demo("d2");

        d1.start();
        d2.start();
    }


}

class Demo extends Thread {

    private String name;

    Demo(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(name + "++++++++");
        }
    }
}


