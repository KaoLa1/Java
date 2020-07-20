package com.muye.exercise.java_01_thread.thread.mydemo;

public class ThreadWithImpliment implements Runnable {

	@Override
	public void run() {
		System.out.println("线程的run方法被调用……");
	}

	public static void main(String[] args) {
		Thread thread = new Thread(new ThreadWithImpliment());
		thread.start();
	}
}
