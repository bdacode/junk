

/**
 * @author mpermana
 * 
 */
public class TestMemory {

	private static long usedMemory() {
		Runtime runtime = Runtime.getRuntime();
		return runtime.totalMemory() - runtime.freeMemory();
	}

	public static void main(String[] args) {
		int COUNT = 1000*1000;
		Season[] values = Season.values();
		System.out.println("usedMemory="+usedMemory());
		for (int i = 0; i < COUNT; i++) {
			Season season = values[i%values.length];
			season.ordinal();
		}
		System.out.println("usedMemory="+usedMemory());
	}

}
