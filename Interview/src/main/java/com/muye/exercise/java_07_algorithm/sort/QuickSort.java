package com.muye.exercise.java_07_algorithm.sort;

public class QuickSort {
	public static void main(String[] args) {
		int[] a = {4,3,1,2};
		sort(a,0,a.length-1);
		for(int x:a) {
			System.out.println(x);
		}
	}
	
	public static int divide(int a[],int start,int end) {
		int base =a[end];
		while(start<end) {
			
			while(start<end&&a[start]<=base) {
				start++;
			}
			if(start<end) {
				int temp = a[start];
				a[start] = a[end];
				a[end] = temp;
				end--;
			}
			
			
			while(start<end&&a[end]>=base) {
					end--;
				}
				if(start<end) {
					int temp = a[start];
					a[start] = a[end];
					a[end] = temp;
					start++;
				}
		}
		return end;
	}
	
	public static void sort(int[] b,int start,int end) {
		if(start>end) {
			return;
		}else {
			int x = divide(b, start, end);
			divide(b, x+1, end);
			divide(b, start, x-1);
		}
	}
	
	
	
	
	
	
	
	
	
}
