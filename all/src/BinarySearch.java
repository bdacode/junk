

public class BinarySearch {

	static int find(int i,int []array) {
		int min=0;
		int max=array.length;
		while (max > min) {
			int mid = min+(max-min)/2;
			if (i == array[mid])
				return mid;
			if (i < array[mid])
				max = mid-1;
			else
				min = mid+1;
		}
		return -1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [] array1 = {0,9,10,11,22};
		System.out.println(find(0,array1));
		System.out.println(find(10,array1));
		System.out.println(find(20,array1));
	}

}
