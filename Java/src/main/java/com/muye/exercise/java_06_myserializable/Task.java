package com.muye.exercise.java_06_myserializable;

import java.io.Serializable;
import java.util.Date;

public class Task implements Runnable, Serializable {

	private static final long serialVersionUID = 1L;

	public void run() {
		System.out.println(new Date());
	}

}
