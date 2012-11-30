import java.util.HashMap;
import java.util.Map.Entry;


public class TestHashMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String,String> map = new HashMap<String,String>();
		
		for (int i=0;i<100;i++) {
			map.put(String.valueOf(i), String.valueOf(i));
		}

		
		for (Entry<String,String> e : map.entrySet()) {
			System.out.print(e.getKey()+",");
			System.out.println(e.getValue());
			
		}

	}

}
