package clearslide;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;


public class TestTreeMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeMap<Date,String> map = new TreeMap<Date,String>();
		Random r = new Random();
		for (int i=0;i<100;i++) {
			map.put(new Date((long)r.nextLong()), String.valueOf(i));
		}

		//
		for (Entry<Date,String> e : map.entrySet()) {
			System.out.print(e.getKey().getTime()+",");
			System.out.println(e.getValue());			
		}

	}

}
