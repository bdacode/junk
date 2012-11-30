package clearslide;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;



public class ClearSlideProblem4 {

	/**
	 * print the birthdate of the person and his children in a table, recursively
	 */
	String printDescendantsAsHTMLTable(Person person) {
		StringBuffer result = new StringBuffer();
		result.append("<TABLE>");
		result.append("<TR>");
		result.append("<TD>");
		result.append("My mother: "+person.mom+",");
		result.append("My father: "+person.dad+",");
		result.append("My birthdate: "+person.birthdate);
		result.append("</TD>");
		result.append("</TR>");
		
		result.append("<TR>");
		ArrayList<Person> children = person.children;
		for (Person child : children) {
			result.append("<TD>");
			printDescendantsAsHTMLTable(child);
			result.append("</TD>");
		}
		result.append("</TR>");
		
		result.append("</TABLE>");
		return result.toString();
	}
	
	public ArrayList createArrayListOfEveryone() {		
		TreeMap<Date, Person> map = new TreeMap<Date, Person>();		
		return new ArrayList(map.values());
	}
	
	/**
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		Person p = new Person();
		
	}


}
