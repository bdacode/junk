package clearslide;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {		
		long l = o1.getBirthdate().getTime() - o2.getBirthdate().getTime();
		if (l > 0)
			return 1;
		else if (l == 0)
			return 0;
		else
			return -1;
	}

}
