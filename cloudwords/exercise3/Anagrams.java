import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;


public class Anagrams {

	static String[] input = {
			"act",
			"apres",
			"asper",
			"bat",
			"cat",
			"dog",
			"god",
			"gods",
			"ham",
			"joke",
			"late",
			"latte",
			"mane",
			"name",
			"parse",
			"pits",
			"save",
			"spare",
			"spit",
			"tab",
			"table",
			"table", // why does table show up twice???
			"tale",
			"vase",
	}; // this is generated with.html and unix commands
	// echo vase bat gods latte name apres spit joke ham dog act tale parse pits asper tab table mane late god cat table save spare | tr ' ' '\n' | sort 
	
	static HashMap<String,HashSet<String>> anagramMap = new HashMap<String,HashSet<String>>();
	
	public static void main(String[] args) {
		if (args.length > 0)
			input = args; // you can also specify input as command line arguments
		
		boolean showAll = false;
		for (String string : input) {
			
			char[] chars = string.toCharArray();
			Arrays.sort(chars);
			String anagram = new String(chars);
			HashSet set = anagramMap.get(anagram);
			if (set == null) {
				set = new HashSet<String>();
				anagramMap.put(anagram,set);
			}
			set.add(string);
		}

		Collection<HashSet<String>> values = anagramMap.values();
		for (HashSet<String> set : values) {
			if (showAll || set.size() > 1) {
				String p = "";
				for (String string : set) {
					System.out.print(p+string);
					p = " ";
				}
				System.out.println();
			}
		}
	}
}
