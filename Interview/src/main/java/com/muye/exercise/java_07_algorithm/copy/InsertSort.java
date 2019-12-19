package com.muye.exercise.java_07_algorithm.copy;

public class InsertSort {
	public static void main(String[] args) {
		int[] b = { 32, 43, 23, 13, 5 };
		insertSort(b);
	}

	public static void insertSort(int[] a) {
		int length = a.length;// ���鳤�ȣ��������ȡ������Ϊ������ٶȡ�
		int insertNum;// Ҫ�������
		for (int i = 1; i < length; i++) {// ����Ĵ���
			insertNum = a[i];// Ҫ�������
			int j = i - 1;// �Ѿ�����õ�����Ԫ�ظ���
			while (j >= 0 && a[j] > insertNum) {// ���дӺ�ǰѭ����������insertNum��������ƶ�һ��
				a[j + 1] = a[j];// Ԫ���ƶ�һ��
				j--;
			}
			a[j + 1] = insertNum;// ����Ҫ�����������Ҫ�����λ�á�
		}
		for(int x : a) {
			System.out.print(x+" ");
		}
	}
}
