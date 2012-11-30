import java.util.HashMap;
import java.util.Map;


public class Palantir {

	public static void main(String[] args) {
		int vals[] = new int[5];
		HashMap<Integer,Integer> countOccurrence = new HashMap<Integer,Integer>();
		for (int i : vals) {
			Integer count = countOccurrence.get(i);
			if (null == count) {
				 countOccurrence.put(i,1); // auto boxing 
			} else {
				 countOccurrence.put(i,1+count); // auto boxing 	
			}	
		}

		
		for (Map.Entry<Integer, Integer>  entry : countOccurrence.entrySet()) {
			System.out.print(entry.getKey() +".");
			System.out.println(entry.getValue());
		}		
	}
}

/**
 * 

// “123” -> 123
int atoi(char* s) {
	int multiplier = 10;
	int result = 0;
	int negative = 1;
	if (‘-’ == *s) {
		negative = -1;
		s++;
	}
	while (*s) {
		int digit = (*s)-’0’;
		result *= multiplier;
		result += digit;
		s++;
	}
return result*negative;
 }

// what about multi thread?
int api_errno = 0;


//------------------------------------

char* itoa(int_large n) {
// special case here

	int howmanydigits = 0;
	bool negative = n < 0;
	int_large temp = n;
	int_large temp2;
	if (negative) {
		temp = -n;
	}
	temp2 = temp;
// temp=0..9 -> 1
// temp=10..99 -> 2
	howmanydigits = 1;
	while (temp > 9) {
		temp /= 10;
		howmanydigits++;
	}
// so many ways
	do {
		temp /= 10;
		howmanydigits++;
	} while (temp > 0);
	
	if (negative) {
		// allocate 1 for ‘-’
	howmanydigits++;
	}
	char *buffer = new char[howmanydigits+1];
	buffer[howmanydigits] = 0; // null terminator
	int i = howmanydigits;

	while (i) {
		i--;
		buffer[i] = temp2 % 10;
		temp2 /= 10;
		if (i == 1 && negative) {
			buffer[0] = ‘-’;
			i--;
		}
	}
	
	return buffer;
}

//---------------------------------------------------

List Set Map

//List<Integer> vals = new ArrayList<Integer>();

int[] vals = { 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3 };

// 1way:
// sort , this takes n lg n
// count consecutive same elements

// 2way
// iterate through the arryy, add each count to a HashMap, this takes n

HashMap<Integer,Integer> countOccurrence = new HashMap<Integer,Integer>();
for (int i : vals) {
	Integer count = countOccurrence.get(i);
	if (null == count) {
		 countOccurrence.put(i,1); // auto boxing 
	} else {
		 countOccurrence.put(i,1+count); // auto boxing 	
	}	
}

for (Map.Entry<Integer,Integer> entry : countOccurrence.entrySet()) {
	System.out.print(entry.key()+”,”);
	System.out.println(entry.value());
}


//------------------------------------------
// write singleton
public class Settings {

	private static Settings instance;

     public static Settings getInstance() {
		if (instance == null)
			// thread 1 here, and thread 2 here
			createInstance();			
		return instance; // might return from thread1 and thread2 diffrent instance
     }

private static synchronized void createInstance() {
if (null == instance)
instance = new Settings();
}
}
}

//----------------------------------------
// write equal for float
public boolean areEqual(float x, float y,float marginOfError) {
	double difference = Math.abs(x-y);
	return difference < marginOfError;
}

*/
