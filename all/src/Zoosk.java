import junit.framework.TestCase;

public class Zoosk extends TestCase {

	static int findNumberOfCharsInText(String chars, String text) {
		int count = 0;
		/**
		 * http://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
		 * char: The char data type is a single 16-bit Unicode character.
		 * It has a minimum value of '\u0000' (or 0) and a maximum value of '\uffff' (or 65,535 inclusive).
		 */
		int countThisChar[] = new int[65536];
		char[] array = chars.toCharArray();
		for (int i = 0; i < array.length; i++) {
			 char c = array[i];
			 countThisChar[c]++;
		}

		array = text.toCharArray();
		for (int i = 0; i < array.length; i++) {
			char c = array[i];
			if (countThisChar[c] > 0)
				count++;
		}

		return count;
	}

	public void testSimple() {
		int result;
		result = findNumberOfCharsInText("abcc", "cat");
		assertTrue(result == 2);
		System.out.println(result);
		
		result = findNumberOfCharsInText("abcc", "catcatcat");
		assertTrue(result == 6);
		System.out.println(result);
	}


}
