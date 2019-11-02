package java_07_algorithm.search;

/**
 * @author gwh
 *
 */
public class BinarySearch {
	public static void main(String[] args) {
		int[] a = {1,2,3,4,5,6,7,8};
		System.out.println(search(a,3));
	}
	
	/**
	 * �۰����
	 */
	static int search(int[] a,int x) {
		int left = 0;
		int right = a.length-1;
		int middle = (left+right)/2;
		while(left<=right) {
			if(a[middle] ==x) {
				return middle;
			}else if(a[middle]<x) {
				left = middle+1;
			}else {
				right = middle-1;
			}
			middle = (left+right)/2;
		}
		return middle;
	}
}
