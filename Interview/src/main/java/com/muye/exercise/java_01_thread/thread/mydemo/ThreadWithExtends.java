package com.muye.exercise.java_01_thread.thread.mydemo;

class ThreadWithExtends extends Thread {
    private String name;

    public ThreadWithExtends(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " 线程的run方法被调用……" + name);
        }
    }
}

class Thread2 {
    public static void main(String[] args) {
        Thread thread1 = new ThreadWithExtends("thread1");
        Thread thread2 = new ThreadWithExtends("thread2");
        thread1.start();
        thread2.start();
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + " main method run ..." + i);
        }
    }
}



