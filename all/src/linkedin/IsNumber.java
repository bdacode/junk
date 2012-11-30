package linkedin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsNumber {

	public static boolean isNumber1(String s) {
		Pattern pattern = Pattern.compile("(\\+|\\-)?\\d+(\\.\\d+)?");
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	public static boolean isNumber(String s) {
		boolean seenComma = false;
		for (int i=0;i<s.length();i++) {
			
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isNumber(""));
		System.out.println(isNumber("0"));
		System.out.println(isNumber("+0"));
		System.out.println(isNumber("-0"));
		System.out.println(isNumber("0.1"));
		System.out.println(isNumber("+0.1"));
		System.out.println(isNumber("-0.1"));
	}

}
