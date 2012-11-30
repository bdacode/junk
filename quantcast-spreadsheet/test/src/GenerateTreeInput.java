
public class GenerateTreeInput {
	
	public static void main(String[] args) {
		int n = 10;
		int m = 27;

		//
		// 0    A1+1  A2+1 A3+1 ....
		// A1+1 B1+A2  
		
		
		System.out.println(n+" "+m);

		System.out.println("0");
		for (int j=1;j<n;j++) {
			System.out.println("A"+j+" 1 +");
		}
		for (int i=1;i<m;i++) {
			for (int j=0;j<n;j++) {
				System.out.print(((char)('A'+i-1))+String.valueOf(j+1)+" 1 +");
				if (j > 0)
					System.out.print(" "+((char)('A'+i-1))+String.valueOf(j+2)+" +");
				System.out.println();
			}
		}
	}
	
}
