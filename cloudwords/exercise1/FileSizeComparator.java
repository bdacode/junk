import java.io.File;

public class FileSizeComparator implements java.util.Comparator<File> {

	int greater = 1;
	int lesser = -1;

	FileSizeComparator(boolean ascending) {
		if (!ascending) {
			greater = -1;
			lesser = 1;
		}
	}

	public int compare(File f1, File f2) {

		long l1 = f1.length();
		long l2 = f2.length();
		if (l1 > l2)
			return greater;
		else if (l1 < l2)
			return lesser;
		return 0;
	}


}
