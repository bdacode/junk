import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Cell {

	String input;
	String name;
	
	int row;
	int col;

	double evaluatedValue;
	boolean evaluated;

	ArrayList<String> references = new ArrayList<String>();
	ArrayList<Token> tokens = new ArrayList<Token>();
	
	volatile transient int edgeCount;

	static String fromCol(int i) {
		return String.valueOf(i+1);
	}
	
	static int toCol(String s) {
		return Integer.valueOf(s)-1;
	}
	
	static int rowi[] = new int[] {0,26,702,18278,Cell.toRow("aaaaa"),Cell.toRow("aaaaaa"),Cell.toRow("aaaaaaa"),};

	static String fromRow(int i) {
		int digits = 0;
		while (rowi[digits] <= i) digits++;
		int offset = i - rowi[digits-1];
		String r = "";
		do {
			char c = (char)(offset%26 +'A');
			offset /= 26;
			r = c + r;
			digits --;
		} while (digits > 0);
		return r;
		/*
		if (i < 26) {
			return String.valueOf((char)('A'+i));			
		} else if (i < rowi[1]) {
			int j = i-rowi[0];
			int x = j/26;
			int y = j%26;
			return String.valueOf((char)('A'+x))+String.valueOf((char)('A'+y));
		}
			return "idk";		
		*/
	}
	static int toRow(String s) {
		int index = 0;
		char[] S = s.toUpperCase().toCharArray();
		for (int i=0;i<S.length;i++) {
			index = index*26+S[i]-'A'+1;
		}
		index--;
		return index;
	}
	
	Cell(int row,int col) {
		this.row = row;
		this.col = col;
		this.name = fromRow(row) + fromCol(col);
	}
	
	public void setInput(String input) {
		this.input = input;
		StringTokenizer st = new StringTokenizer(input);
		while (st.hasMoreTokens()) {
			String expression = st.nextToken();
			Token token = Token.parseToken(expression);
			if (token.isReference())
				references.add(expression);
			tokens.add(token);
		}
	}

	public double evaluate() throws CircularDependancyException {		
		if (evaluated)
			return evaluatedValue;

		Stack<Double> stack = new Stack<Double>();
		double eval, arg2, arg1;
		for (Token token : tokens) {
			switch (token.type) {
			case 0:
				stack.push(token.value);
				break;
			case 1:
				Cell reference = Spreadsheet.cells[token.referenceRow][token.referenceColumn];
				eval = reference.evaluate();
				stack.push(eval);
				break;
			case 2:
				arg1 = stack.pop();
				arg2 = stack.pop();
				eval = arg2 + arg1;
				stack.push(eval);
				break;
			case 3:
				arg1 = stack.pop();
				arg2 = stack.pop();
				eval = arg2 - arg1;
				stack.push(eval);
				break;
			case 4:
				arg1 = stack.pop();
				arg2 = stack.pop();
				eval = arg2 * arg1;
				stack.push(eval);
				break;
			case 5:
				arg1 = stack.pop();
				arg2 = stack.pop();
				eval = arg2 / arg1;
				stack.push(eval);
				break;
			case 6:
				arg1 = stack.pop();
				eval = arg1 + 1;
				stack.push(eval);
				break;
			case 7:
				arg1 = stack.pop();
				eval = arg1 - 1;
				stack.push(eval);
				break;
			}
		}
		evaluatedValue = stack.pop();
		evaluated = true;
		return evaluatedValue;
	}

	public String toString() {
		return name;
	}
}
