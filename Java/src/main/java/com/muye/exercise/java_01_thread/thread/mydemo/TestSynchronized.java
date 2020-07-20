package com.muye.exercise.java_01_thread.thread.mydemo;

public class TestSynchronized {
	public static void main(String[] args) {
		final TestSynchronized testSynchronized = new TestSynchronized();

		new Thread() {
			@Override
			public void run() {
				synchronized (testSynchronized) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					System.out.println("thread1,start");
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				synchronized (testSynchronized) {
					System.out.println("thread2,start");
				}
			}
		}.start();
	}

}
