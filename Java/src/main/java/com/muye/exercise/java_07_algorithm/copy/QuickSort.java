package com.muye.exercise.java_07_algorithm.copy;

/**
 * ��������
 * 
 * @author IT_ZJYANG
 */
public class QuickSort {

	public static void main(String[] args) {

		int[] a = new int[] { 2, 7, 4, 5, 10, 1, 9, 3, 8, 6 };
		int[] b = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] c = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] d = new int[] { 1, 10, 2, 9, 3, 2, 4, 7, 5, 6 };

		sort(a, 0, a.length - 1);

		System.out.println("�����Ľ����");
		for (int x : a) {
			System.out.print(x + " ");
		}
	}

	/**
	 * �������ĳһ��Ԫ�ؽ��л��֣�С������ߣ�������ұ�
	 * 
	 * @param a
	 * @param start
	 * @param end
	 * @return
	 */
	public static int divide(int[] a, int start, int end) {
		// ÿ�ζ������ұߵ�Ԫ����Ϊ��׼ֵ
		int base = a[end];
		// startһ������end����˵����������ָ��ϲ�����ͬһλ�ã����Խ�������ѭ����
		while (start < end) {
			while (start < end && a[start] <= base)
				// ����߿�ʼ����������Ȼ�׼ֵС���ͼ���������
				start++;
			// �����whileѭ������ʱ����˵����ǰ��a[start]��ֵ�Ȼ�׼ֵ��Ӧ���׼ֵ���н���
			if (start < end) {
				// ����
				int temp = a[start];
				a[start] = a[end];
				a[end] = temp;
				// �����󣬴�ʱ���Ǹ���������ֵҲͬʱ��������ȷ��λ��(��׼ֵ�ұ�)������ұ�ҲҪͬʱ��ǰ�ƶ�һλ
				end--;
			}
			while (start < end && a[end] >= base)
				// ���ұ߿�ʼ����������Ȼ�׼ֵ�󣬾ͼ���������
				end--;
			// �����whileѭ������ʱ����˵����ǰ��a[end]��ֵ�Ȼ�׼ֵС��Ӧ���׼ֵ���н���
			if (start < end) {
				// ����
				int temp = a[start];
				a[start] = a[end];
				a[end] = temp;
				// �����󣬴�ʱ���Ǹ���������ֵҲͬʱ��������ȷ��λ��(��׼ֵ���)��������ҲҪͬʱ����ƶ�һλ
				start++;
			}

		}
		// ���ﷵ��start����end�Կɣ���ʱ��start��end��Ϊ��׼ֵ���ڵ�λ��
		return end;
	}

	/**
	 * ����
	 * 
	 * @param a
	 * @param start
	 * @param end
	 */
	public static void sort(int[] a, int start, int end) {
		if (start > end) {
			// ���ֻ��һ��Ԫ�أ��Ͳ���������ȥ��
			return;
		} else {
			// �����ֹһ��Ԫ�أ������������ߵݹ�������ȥ
			int partition = divide(a, start, end);
			sort(a, start, partition - 1);
			sort(a, partition + 1, end);
		}

	}

}
