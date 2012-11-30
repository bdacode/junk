package netsuite;

public class Problem2 {

	static double square_root(double a, double epsilon) {
		if (Math.abs(a) < epsilon)
			return 0;

		double x = 1.5; 
		
		while (true) {
			if (Math.abs(x*x - a) < epsilon)
				return x;
			double y = a/x;
			x = (x+y)/2;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(square_root(2, 1e-6));
	}
	
}