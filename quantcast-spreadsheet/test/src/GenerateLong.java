
public class GenerateLong {
	
	public static void main(String[] args) {
		int n = 100000;
		int m = 5;

		System.out.println(n+" "+m);
		for (int i=0;i<n;i++) {
			System.out.println(i);
		}
		for (int i=0;i<n;i++) {
			System.out.println("A"+(i+1)+" "+"A"+(i+1)+" +");
		}
		for (int i=0;i<n;i++) {
			System.out.println("A"+(i+1)+" "+"A"+(i+1)+" -");
		}
		for (int i=0;i<n;i++) {
			System.out.println("A"+(i+1)+" "+"A"+(i+1)+" *");
		}
		for (int i=0;i<n;i++) {
			System.out.println("A"+(i+1)+" "+"A"+(i+1)+" /");
		}

	}
	
}
