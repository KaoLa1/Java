package com.muye.exercise.java_07_algorithm.sort;

/**
 * @author gwh   �����㷨��ϰ
 *
 */
public class BubbleSort {

	/**
	 * ð������
	 */
	static void bubblesort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - i - 1; j++) {
				int tmp;
				if (a[j] > a[j + 1]) {
					tmp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = tmp;
				}
			}
		}
		
		for(int b :a) {
			System.out.println(b);
		}
	}
	
	public static void main(String[] args) {
		int[] a = { 1, 3, 5, 2, 4, 9, 8, 6, 7 };
		bubblesort(a);
		
	}

}
