package com.muye.thread.thread;

/**
 * 对程序进行优化。因为资源类中的数据通常都需要私有。
 * 通过公有的方法对其进行访问。
 */
class ThreadDemo12 {
    public static void main(String[] args) {
        Res r = new Res();
        new Thread(new Input(r)).start();
        new Thread(new Output(r)).start();

		/*
		Input in = new Input(r);
		Output out = new Output(r);
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		t1.start();
		t2.start();
		*/
    }
}

class Res12 {
    private String name;
    private String sex;
    private boolean b;

    public synchronized void set(String name, String sex) {
      /*  if (b) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }*/
        this.name = name;

        this.sex = sex;
       /* b = true;
        this.notify();*/
    }

    public synchronized void out() {
        /*if (!b) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }*/
        System.out.println(name + "-----" + sex);
     /*   b = false;
        this.notify();*/
    }
}

class Input12 implements Runnable {
    private Res12 r;

    Input12(Res12 r) {
        this.r = r;
    }

    @Override
    public void run() {
        int x = 0;

        while (true) {
            if (x == 0) {
                r.set("Mike", "nan");
            } else {
                r.set("李丽丽", "女女女女");
            }
            x = (x + 1) % 2;
        }
    }
}


class Output12 implements Runnable {
    private Res12 r;

    Output12(Res12 r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            r.out();
        }
    }
}

