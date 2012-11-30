import java.util.Random;

public class MinMax {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 11;
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = (int) (new Random().nextInt() % 100);
			System.out.print(array[i] + " ");
		}
		System.out.println();

		// int[] xarray = {15,66,7,-21,69,-5,-54,-66,68,-51,4};
		// array = xarray;

		int half = (n + 1) / 2;
		int[] mins = new int[half];
		int[] maxs = new int[half];
		int min = array[0];
		int max = array[0];

		for (int i = 0; i < half; i++) {
			int il = i * 2;
			int ir = i * 2 + 1;
			if (ir >= array.length) {
				ir = il;
			}
			if (array[il] > array[ir]) {
				mins[i] = array[ir];
				maxs[i] = array[il];
			} else {
				mins[i] = array[il];
				maxs[i] = array[ir];
			}

			if (mins[i] < min)
				min = mins[i];
			if (maxs[i] > max) {
				max = maxs[i];
				System.out.println("setting max to " + max);
			}
		}

		System.out.println("min=" + min + ",max=" + max);

	}

}
