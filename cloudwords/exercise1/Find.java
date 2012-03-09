import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class Find {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage:");
			System.out.println(" java -cp classpath Find [directoryname]");
			return;
		}
		
		ArrayList<File> resultSet = new ArrayList<File>();

		LinkedList<File> directories = new LinkedList<File>();
		
		File f = new File(args[0]);
		
		if (!f.isDirectory()) {
			System.out.println("Error: not a directory "+args[1]);
			return;
		}
		
		directories.add(f);
		while (!directories.isEmpty()) {
			File directory = directories.removeFirst();
			File[] list = directory.listFiles();
			if (list == null)
				continue;
			for (File file : list) {
				if (file.isDirectory()) {
					directories.add(file);				
				} else {
					resultSet.add(file);
				}
			}
		}
		
		boolean ascending = true;
		Collections.sort(resultSet,new FileSizeComparator(ascending));
		for (File file : resultSet) {
			try {
				String path = file.getCanonicalPath(); // i dont separate path and the filename
				long filesize = file.length();
				String line = String.format("%12s %s",filesize,path); // i like this format, 
				System.out.println(line);
			} catch (IOException e) {
				// i print stack trace in case of io error 
				e.printStackTrace();
			}
		}
		
	}
	
}
