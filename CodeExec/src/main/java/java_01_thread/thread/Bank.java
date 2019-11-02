package java_01_thread.thread;/*
需求：
两个人各有300元，分三次每次100元，存入银行。

*/

class Bank {
    private int sum;

    //Object obj = new Object();
    public synchronized void add(int n,String name)//同步函数
    {
        //synchronized(obj)
        //{
        sum = sum + n;
        try {
            Thread.sleep(10);
        } catch (Exception e) {
        }
        System.out.println(name+"存了："+"sum=" + sum);
        //}
    }
}

class Consumer implements Runnable {
    private String name;
    public Consumer(String name){
        this.name = name;
    }
    private Bank b = new Bank();

    @Override
    public void run() {
        for (int x = 0; x < 3; x++) {
            b.add(100,name);
        }
    }
}


class ThreadTest2 {
    public static void main(String[] args) {
        //必然要建立Consumer的对象
        //因为要把Runnable接口的子类对象作为参数传递给Thread类的构造函数。
        Consumer c1 = new Consumer("zhangsan");
        Consumer c2 = new Consumer("lisi");

        //创建线程对象。
//		Thread t1 = new Thread(c);
//		t1.start();
        new Thread(c1).start();
        new Thread(c2).start();

    }
}
