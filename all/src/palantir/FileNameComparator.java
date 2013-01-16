package palantir;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implements Comparator<String> so that it can compare two string.
 * @author mpermana
 */
public class FileNameComparator implements Comparator<String> {

	/**
	 * Do comparison of string s1 and s2 with natural order of tokens in the string.
	 * 
	 * The string comparison is done by breaking the string into multiple tokens and comparing each token so that
	 * ordering can be done for each token type.
	 * 
	 * This gives us ability to order token of type numbers with natural number ordering, so that 2 comes before 10.
	 * Recognized tokens are whitespace, number, word and emails.
	 * 
	 * Since the requirements leaves open of natural sort ordering for end user, I added these ordering:
	 * 
	 * - For words, the ordering is case insensitive.
	 *	 The default ascii ordering is case sensitive, i.e A < Z < a < Z, however I dont think this is
	 *   intuitive. It is more natural to see the ordering like : [Apple,apple,Palantir,palantir]
	 * - I add ability to recognize EMAIL !!! And it's very easy to modify my program to recognize other pattern
	 *   using regular expression.
	 *   My reasoning is email is more natural if you sort them by their domain name, so instead of seeing:
	 *       a@apple.com, a@palantir.com, z@apple.com, z@palantir.com
	 *   I prefer to see them sorted by their domain name, first comes everyone from apple.com, then everyone from palantir.com:
	 *   	 a@apple.com, email z@apple.com, email a@palantir.com, email z@palantir.com
	 */
	public int compare(String s1, String s2) {
		if (s1.equals(s2))
			return 0;

		List<Token> tokens1 = tokenize(s1, rules);
		List<Token> tokens2 = tokenize(s2, rules);

		// find first token that differs
		int minLength = Math.min(tokens1.size(), tokens2.size());
		for (int i = 0; i < minLength; i++) {
			int diff = tokens1.get(i).compareTo(tokens2.get(i));
			if (diff != 0)
				return diff;
		}
		// if we get here, that means all tokens are the same
		// return the diff between token sizes just like String.compareTo
		return tokens1.size() - tokens2.size();
	}

	/**
	 * private class to break a string into tokens
	 */
	private static class Token {
		enum Type {
			WHITESPACE, NUMBER, WORD, EMAIL
		};

		final Type type;
		final int startPos;
		final int endPos;
		final String string;
		final String username;
		final String domainName;

		Token(Type type, int startPos, int endPos, String string) {
			this.type = type;
			this.startPos = startPos;
			this.endPos = endPos;
			this.string = string;
			if (type == Type.EMAIL) {
				String[] splitted = string.split("@");
				this.username = splitted[0].toLowerCase();
				this.domainName = splitted[1].toLowerCase();
			} else {
				this.username = null;
				this.domainName = null;
			}
		}

		@Override
		public String toString() {
			return String.format("Token [%2d, %2d, %s] %s", startPos, endPos,
					type, string);
		}

		/**
		 * The actual function the does comparison between tokens.
		 * If tokens are the same type, than additional token specific comparison is done.
		 * I.e number tokens are compared as BigDecimal
		 * @return
		 */
		public int compareTo(Token token) {
			int diff = 0;
			if (type == token.type) {
				switch (type) {
				case NUMBER:
					// both are numbers, we can compare as BigDecimal
					BigDecimal bd1 = new BigDecimal(string);
					BigDecimal bd2 = new BigDecimal(token.string);
					diff = bd1.compareTo(bd2);
					if (diff != 0) {
						return diff;
					} else {
						return string.length() - token.string.length();
					}
				case WORD:
					// do comparison in all lowercase
					String lowerCase1 = string.toLowerCase();
					String lowerCase2 = token.string.toLowerCase();
					diff = lowerCase1.compareTo(lowerCase2);
					break;
				case EMAIL:
					// compare domain names first
					diff = domainName.compareTo(token.domainName);
					if (diff != 0)
						return diff;
					// then compare username
					diff = username.compareTo(token.username);
					if (diff != 0)
						return diff;
				}
			}
			if (diff != 0)
				return diff;
			// if they are the same proceed with string comparison, i still
			// want to differentiate between '007' and '7' or 'apple' and
			// 'palantir'
			return string.compareTo(token.string);
		}
	}

	static List<Token> tokenize(String source, List<Rule> rules) {
		List<Token> tokens = new ArrayList<Token>();
		int pos = 0;
		final int end = source.length();
		Matcher m = Pattern.compile("dummy").matcher(source);
		m.useTransparentBounds(true).useAnchoringBounds(false);
		while (pos < end) {
			m.region(pos, end);
			for (Rule r : rules) {
				if (m.usePattern(r.pattern).lookingAt()) {
					tokens.add(new Token(r.type, m.start(), m.end(), source
							.substring(m.start(), m.end())));
					pos = m.end();
					break;
				}
			}
		}
		return tokens;
	}

	static class Rule {
		final Token.Type type;
		final Pattern pattern;

		Rule(Token.Type type, String regex) {
			this.type = type;
			pattern = Pattern.compile(regex);
		}
	}

	static List<Rule> rules = new ArrayList<Rule>();

	static {
		rules.add(new Rule(Token.Type.WHITESPACE, "\\s+"));
		rules.add(new Rule(
				Token.Type.EMAIL,
				"[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})"));
		rules.add(new Rule(Token.Type.NUMBER, "\\d+"));
		rules.add(new Rule(Token.Type.WORD, "[^\\d\\s]+"));
	}


}
