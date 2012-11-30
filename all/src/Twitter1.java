import java.util.Date;

public class Twitter1 {

	public static int getLongestIncreasingSubarray(int[] array) {
		if (array == null)
			throw new NullPointerException();
		if (array.length == 0)
			return 0;
		int result = 1;
		int maxResult = result;
		for (int i = 1; i < array.length;) {
			if (array[i] <= array[i - 1]) {
				result = 1;
			} else {
				result++;
			}
			if (result > maxResult)
				maxResult = result;

			i++;
		}

		return maxResult;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date d = new Date();
		System.out.println(getLongestIncreasingSubarray(new int[] { 1, 2, 3,
				1, 0, 0, 1, 3, 5, 6, 7, 8 }));
		System.out.println(getLongestIncreasingSubarray(new int[] { 1, 2, 3,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6 }));

	}

}
