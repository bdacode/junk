import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;


public class Calculator {

	static Integer eval(String operator, Integer op1, Integer op2)
			throws Exception {
		System.out.println("eval "+operator+" "+op1+" "+op2);
		if ("+".equals(operator)) {
			return new Integer(op1.intValue() + op2.intValue());
		} else if ("-".equals(operator)) {
			return new Integer(op1.intValue() - op2.intValue());
		} else if ("*".equals(operator)) {
			return new Integer(op1.intValue() * op2.intValue());
		} else if ("/".equals(operator)) {
			return new Integer(op1.intValue() / op2.intValue());
		}

		throw new Exception("unknown operator " + operator);
	}

	static int getLevel(String operator) {
		int operatorLevel = 0;
		if (-1 != "*/".indexOf(operator.charAt(0))) {
			operatorLevel = 1;
		}
		return operatorLevel;
	}

	static ArrayList<Object> expressions = new ArrayList<Object>();
	
	static void processNumber(Integer number) {
		System.out.println("number " + number);
		expressions.add(number);
	}
	
	static void processOperator(String operator) {
		System.out.println("number " + operator);				
		expressions.add(operator);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String input = "-10 +20*30";
		input = "1*2-3/2*4*5+6-7";
		input = "1*(2-3)/2*4*5+6-7";
		//input = "2-60-1";
		

		Stack<Integer> operandStack = new Stack<Integer>();
		Stack<String> operatorStack = new Stack<String>();
		StringTokenizer st = new StringTokenizer(input, "+-*/", true);
		int parse = 0;
		int level = 0;

		try {
			while (st.hasMoreTokens()) {
				String token = st.nextToken().trim();
				if (parse == 0) {
					// get expression
					if ("-".equals(token)) {
						// unary minus
						token = "-" + st.nextToken().trim();
					}
					Integer number = new Integer(token);
					operandStack.push(number);
					processNumber(number);
					parse = 1;
				} else {
					// get operator
					String operator = token;
					processOperator(operator);
					operatorStack.push(operator);
					parse = 0;
				}
			}


			// process ( )			)
			for (int i=0;i<expressions.size();) {
				Object o = expressions.get(i);
				if (")".equals(o)) {
					i = processParenthesis(i);
				} else {
					i++;
				}
			}
			
			processExpression(0,expressions.size());
			
			// process expressions * /
			for (int i=0;i<expressions.size();) {
				Object o = expressions.get(i);
				if (-1 != "*/".indexOf(o.toString().charAt(0))) {					
					// remove previous this and next
					Integer op1 = (Integer)expressions.get(i-1);
					Integer op2 = (Integer)expressions.get(i+1);
					Integer result = eval(o.toString(), op1, op2);
					expressions.set(i-1, result);
					expressions.remove(i);
					expressions.remove(i);
				} else {
					i++;
				}				
			}
			for (int i=0;i<expressions.size();) {
				Object o = expressions.get(i);
				if (-1 != "+-".indexOf(o.toString().charAt(0))) {					
					// remove previous this and next
					Integer op1 = (Integer)expressions.get(i-1);
					Integer op2 = (Integer)expressions.get(i+1);
					Integer result = eval(o.toString(), op1, op2);
					expressions.set(i-1, result);
					expressions.remove(i);
					expressions.remove(i);
				} else {
					i++;
				}				
			}
			System.out.println(expressions.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void processExpression(int i, int size) {
		// TODO Auto-generated method stub
		
	}

	private static int processParenthesis(int i) {
		//(1+2)
		ArrayList<Object> subexpression = new ArrayList<Object>();
		
		return 0;
	}

}
