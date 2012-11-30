package google;


public class FindLongestCommonPrefix {

	public static void main(String[] args) {
		String result = "";
		if (args == null || args.length == 0) {
			result = "";
			return;
		}
		if (args.length == 1) {
			result = args[0];
			return;
		}
		result = args[0];
		for (int checkAtCharI = 0;checkAtCharI<result.length();checkAtCharI++) {
			for (int i=1;i<args.length;i++) {
				if (result.charAt(checkAtCharI) != args[i].charAt(checkAtCharI) || i >= args[i].length()) {
					result = result.substring(0,i);
					break;
				}
			}
		}
		
		System.out.println(result);
	}
	
}
