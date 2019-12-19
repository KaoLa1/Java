package com.muye.exercise.java_08_designpattern;

public class HSingleton {
	private static HSingleton instance = new HSingleton();

	private HSingleton() {
	}

	public static HSingleton getInstance() {
		return instance;

	}

}
