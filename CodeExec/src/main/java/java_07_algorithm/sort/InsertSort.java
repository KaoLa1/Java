package java_07_algorithm.sort;

public class InsertSort {
	public static void main(String[] args) {
		int a[] = { 32, 43, 23, 13, 5 };
		sort(a);
	}

	public static void sort(int[] b) {
		int insertNum;
		for (int i = 1; i < b.length; i++) {
			insertNum = b[i]; // Ҫ�������
			int j = i - 1; // �Ѿ��ź������
			while (j >= 0 && b[j] > insertNum) {
				b[j + 1] = b[j];
				j--;
			}
			b[j + 1] = insertNum;
		}
		for (int x : b) {
			System.out.print(x+" ");
		}
	}
}
