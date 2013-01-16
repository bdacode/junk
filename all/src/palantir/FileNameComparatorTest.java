package palantir;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class FileNameComparatorTest {

	// provide static instance for test methods
	static FileNameComparator fileNameComparator = new FileNameComparator();

	@Test
	public void testEmptyString() {
		Assert.assertTrue(fileNameComparator.compare("", "empty") < 0);
		Assert.assertTrue(fileNameComparator.compare("empty", "") > 0);
	}

	@Test
	public void testStringWithSamePrefix() {
		Assert.assertTrue(fileNameComparator.compare("prefix suffix_a",
				"prefix suffix_b") < 0);
		Assert.assertTrue(fileNameComparator.compare("prefix suffix_b",
				"prefix suffix_a") > 0);
	}

	@Test
	public void testNaturalOrdering() {
		Assert.assertTrue(fileNameComparator.compare("1", "2") < 0);
		Assert.assertTrue(fileNameComparator.compare("2", "1") > 0);
		Assert.assertTrue(fileNameComparator.compare("1", "10") < 0);
		Assert.assertTrue(fileNameComparator.compare("10", "1") > 0);
		Assert.assertTrue(fileNameComparator.compare("2", "10") < 0);
		Assert.assertTrue(fileNameComparator.compare("10", "2") > 0);
	}

	@Test
	public void testNaturalOrderingPrefixZero() {
		// 7 and 007 are same natural number
		// the one that has less characters comes first
		// both are less than 8 and more then 6
		Assert.assertTrue(fileNameComparator.compare("7", "007") < 0);
		Assert.assertTrue(fileNameComparator.compare("007", "7") > 0);
		Assert.assertTrue(fileNameComparator.compare("agent 7", "agent 007") < 0);
		Assert.assertTrue(fileNameComparator.compare("agent 007", "agent 7") > 0);

		Assert.assertTrue(fileNameComparator.compare("7", "8") < 0);
		Assert.assertTrue(fileNameComparator.compare("7", "08") < 0);
		Assert.assertTrue(fileNameComparator.compare("7", "0008") < 0);
		Assert.assertTrue(fileNameComparator.compare("007", "6") > 0);
		Assert.assertTrue(fileNameComparator.compare("007", "06") > 0);
		Assert.assertTrue(fileNameComparator.compare("007", "0006") > 0);
	}

	@Test
	public void testEmailSortedByDomainNameAndUsername() {
		Assert.assertTrue(fileNameComparator.compare("Apple@apple.com",
				"apple@apple.com") < 0);
		Assert.assertTrue(fileNameComparator.compare("apple@apple.com",
				"Apple@apple.com") > 0);

		Assert.assertTrue(fileNameComparator.compare("apple@apple.com",
				"palantir@apple.com") < 0);
		Assert.assertTrue(fileNameComparator.compare("palantir@apple.com",
				"apple@apple.com") > 0);
		Assert.assertTrue(fileNameComparator.compare("apple@palantir.com",
				"palantir@palantir.com") < 0);
		Assert.assertTrue(fileNameComparator.compare("palantir@palantir.com",
				"apple@palantir.com") > 0);

		Assert.assertTrue(fileNameComparator.compare("apple@apple.com",
				"palantir@apple.com") < 0);
		Assert.assertTrue(fileNameComparator.compare("palantir@apple.com",
				"apple@apple.com") > 0);
	}

	@Test
	public void test256LengthString() {
		StringBuilder number254Length = new StringBuilder();
		while (number254Length.length() < 254)
			number254Length.append("1");
		String smaller255 = number254Length + "0";
		String larger255 = number254Length + "1";
		String largest255 = "9" + number254Length;

		Assert.assertTrue(fileNameComparator.compare(smaller255, larger255) < 0);
		Assert.assertTrue(fileNameComparator.compare(larger255, smaller255) > 0);
		Assert.assertTrue(fileNameComparator.compare(largest255, larger255) > 0);
	}

	// test on sorting
	@Test
	public void testSortBasicFileNameWithNumbers() {
		String[] testArray = { "test2.txt", "test10.txt", "test1.txt" };
		String[] expectedOutput = { "test1.txt", "test2.txt", "test10.txt" };
		testSorting(testArray, expectedOutput);
	}

	@Test
	public void testSortEmail() {
		String[] testArray = { "email a@apple.com", "email z@apple.com",
				"email a@palantir.com", "email z@palantir.com" };
		String[] expectedOutput = { "email a@apple.com", "email z@apple.com",
				"email a@palantir.com", "email z@palantir.com" };
		testSorting(testArray, expectedOutput);
	}

	@Test
	public void testSortExample() {
		String[] testArray = {"1/5", "1/20", "1 2 10", "1 10 2" };
		String[] expectedOutput = { "1 2 10", "1 10 2", "1/5", "1/20"};
		testSorting(testArray, expectedOutput);

		List<String> reversedInput = Arrays.asList(testArray);
		Collections.reverse(reversedInput);
		testSorting(reversedInput.toArray(new String[testArray.length]),
				expectedOutput);
	}

	@Test
	public void testSortEverything() {
		String[] testArray = { "apple january 9", "apple january 11",
				"apple January 11", "Apple", "palantir", "email a@apple.com",
				"email z@apple.com", "email a@palantir.com",
				"email z@palantir.com", "0.jpeg", "00.jpeg", "00name",
				"00Name", "01.jpeg", "1.jpeg", "file 7", "file 007",
				"file 00007", "file 8", "meeting at 1:00 am",
				"meeting at 2:00 am", "meeting at 10:00 am",
				"meeting at 11: am", "1/5", "1/20", "1 2 10", "1 10 2" };
		String[] expectedOutput = { "0.jpeg", "00.jpeg", "00Name", "00name",
				"1 2 10", "1 10 2", "1.jpeg", "1/5", "1/20", "01.jpeg",
				"Apple", "apple January 11", "apple january 9",
				"apple january 11", "email a@apple.com", "email z@apple.com",
				"email a@palantir.com", "email z@palantir.com", "file 7",
				"file 007", "file 00007", "file 8", "meeting at 1:00 am",
				"meeting at 2:00 am", "meeting at 10:00 am",
				"meeting at 11: am", "palantir" };
		testSorting(testArray, expectedOutput);

		List<String> reversedInput = Arrays.asList(testArray);
		Collections.reverse(reversedInput);
		testSorting(reversedInput.toArray(new String[testArray.length]),
				expectedOutput);
	}

	private void testSorting(String[] testArray, String[] expectedOutput) {
		Arrays.sort(testArray, fileNameComparator);
		System.out.println(Arrays.toString(testArray));
		assertArrayEquals(testArray, expectedOutput);
		assertArrayOrdered(testArray);
	}

	public void assertArrayEquals(String[] array1, String[] array2) {
		Assert.assertTrue(array1.length == array2.length);
		for (int i = 0; i < array2.length; i++) {
			Assert.assertTrue(array1[i].equals(array2[i]));
		}
	}

	public void assertArrayOrdered(String[] array) {
		for (int i = 1; i < array.length; i++) {
			Assert.assertTrue(fileNameComparator
					.compare(array[i - 1], array[i]) < 0);
		}
	}

}
