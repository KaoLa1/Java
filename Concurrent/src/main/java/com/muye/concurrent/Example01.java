package com.muye.concurrent;

public class Example01 extends Thread {
    boolean  stop=false;
    public static void main( String args[] ) throws Exception {
        Example01 thread = new Example01();
        thread.start();
        Thread.sleep(1000);
        thread.interrupt(); //�̲߳���ֹͣ
//      thread.stop = true;  //�̻߳�ֹͣ
    }
    public void run() {
        while(!stop){
            System.out.println( "Thread is running..." );

        }
        System.out.println("Thread exiting..." );
        }
}