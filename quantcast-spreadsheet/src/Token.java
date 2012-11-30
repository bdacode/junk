import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Token {

	Pattern p = Pattern.compile("([a-zA-Z]+)(\\d+)");

	public Token(int type) {
		this.type = type;
	}

	public Token(String reference) {
		Matcher matcher = p.matcher(reference);
		
		if (!matcher.matches())
			throw new RuntimeException("Invalid reference syntax: "+reference);

		type = 1;
		referenceRow = Cell.toRow(matcher.group(1));
		referenceColumn = Integer.valueOf(matcher.group(2))-1;
	}

	public Token(double d) {
		type = 0;
		value = d;
	}

	// 0 = value
	// 1 = reference
	// 2 = operator+
	// 3 = operator-
	// 4 = operator*
	// 5 = operator/
	// 6 = operator ++
	// 7 = operator --
	int type;
	
	int operatorType;
	double value;
	int referenceRow;
	int referenceColumn;
	
	@Override
	public String toString() {
		switch (type) {
		case 0:
			return String.valueOf(value);
		case 1:
			return String.valueOf((char)(referenceRow+65)+referenceColumn);
		case 2:
			return "+";
		case 3:
			return "-";
		case 4:
			return "*";
		case 5:
			return "/";
		case 6:
			return "++";
		case 7:
			return "--";
		}
		return super.toString();
	}

	public static Token parseToken(String t) {
		String token = t.toUpperCase();
		if ("+".equals(token)) {
			return new Token(2);
		} else if ("-".equals(token)) {
			return new Token(3);
		} else if ("*".equals(token)) {
			return new Token(4);
		} else if ("/".equals(token)) {
			return new Token(5);
		} else if ("++".equals(token)) {
			return new Token(6);
		} else if ("--".equals(token)) {
			return new Token(7);
		} else {
			char c = token.charAt(0);
			if ('A' <= c && c <= 'Z') {
				// reference
				return new Token(token);
			} else {
				// integer
				double d = Double.valueOf(token);
				return new Token(d);
			}
		}
	}

	public boolean isReference() {		
		return 1 == type;
	}

}
