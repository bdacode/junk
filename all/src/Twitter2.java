import java.util.Stack;

public class Twitter2 {

	public static boolean check(String s) {
		Stack<Character> stack = new Stack<Character>();
		String openings = "({[<";
		String closings = ")}]>";
		int closingIndex;
		for (char c : s.toCharArray()) {
			if (openings.indexOf(c) != -1) {
				stack.push(c);
			} else {
				closingIndex = closings.indexOf(c);
				if (closingIndex != -1) {
					if (stack.size() == 0)
						return false;					
					char opening = (Character) stack.pop();
					if (opening != openings.charAt(closingIndex))
						return false;
				}
			}
		}
		return stack.size() == 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(check("<t_(w)_i{tt}er>"));

	}

}
