import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;


public class Solution {

	static void save(HashMap<String, String> map,String filename) {
		// writing an object to a file
		try {
			ObjectOutputStream outStr = new ObjectOutputStream(
					new FileOutputStream(filename));
			outStr.writeObject(map);
			outStr.flush();
			outStr.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	static HashMap<String, String> load(String filename) {
		HashMap<String, String> result = null;
		// reading an object from a file
		try {
			ObjectInputStream inStr = new ObjectInputStream(
					new FileInputStream(filename));
			result = (HashMap<String, String>) inStr.readObject();
			inStr.close();
		} catch (Exception ex) {
			result = new HashMap<String, String>();
		}

		return result;
	}

	static void keyValue(String[] a, int n) {
		java.io.File f = new File("/");
		File[] files = f.listFiles();
		for(File file : files)
			System.out.println(file.getName());
	}

	public static void main(String[] args) {
		keyValue(new String[] { "SET  a\t\n	1", "SET b 2", "GET a", "COMMIT",
				"SET b 3" }, 5);
		keyValue(new String[] { "GET b", "SET b 4" }, 2);
		keyValue(new String[] { "GET b" }, 1);
	}
}
