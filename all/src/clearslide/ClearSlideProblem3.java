package clearslide;
import java.math.BigInteger;
import java.util.Hashtable;


public class ClearSlideProblem3 {

	static int MAX = 1000000;
	static Hashtable<BigInteger,BigInteger> chainCount = new Hashtable<BigInteger,BigInteger>(MAX*2);
	static BigInteger big3 = new BigInteger("3");	
	static BigInteger big2 = new BigInteger("2");
	static BigInteger big1 = BigInteger.ONE;
	static BigInteger big0 = BigInteger.ZERO;
	
	
	static BigInteger getCount(BigInteger I) {
		//BigInteger I = new BigInteger(i);
		BigInteger C = chainCount.get(I);
		if (C == null) {
			BigInteger next = nextNumber(I);
			BigInteger count = getCount(next).add(big1);
			C = count;
			chainCount.put(I,C);
			//System.out.println("solved "+I+" count="+count);
		}
		return C;
	}
	
	/**
	 * probably can optimize the equation to compute nextNumber(nextNumber(i))
	 * becomes (n+2n+1)/2
	 * which is n+0.5n+0.5
	 * @param i
	 * @return
	 */
	static BigInteger nextNumber(BigInteger i) {
		if (big0.equals(i.mod(big2))) {
			return i.divide(big2);
		} 
		return i.multiply(big3).add(big1);
		/*
		if (i % 2 == 0) {
			return i/2;
		}
		return i*3+1;
		*/
	}

	public static void main(String[] args) {
		BigInteger maxCount = big1;
		BigInteger maxValue = big1;
		chainCount.put(big1,big1);
		BigInteger I = new BigInteger("2");
		long last = System.currentTimeMillis();
		for (int i=2;i<MAX;i++) {			
			long elapsed = System.currentTimeMillis()-last;
			if (elapsed > 1000) {
				System.out.println("free memory: "+Runtime.getRuntime().freeMemory());
				System.out.println("current Longest starting number is "+maxValue);
				printChain(maxValue);
				last = System.currentTimeMillis();
			}
			BigInteger count = getCount(I);
			if (count.compareTo(maxCount) > 0) {
				maxCount = count;
				maxValue = I;
				System.out.println("found longer chain for "+i);
			}
			I = I.add(big1);
		}
		System.out.println("Longest starting number is "+maxValue);
		printChain(maxValue);
	}

	private static void printChain(BigInteger I) {
		do {
			System.out.print(I+" ");
			I = nextNumber(I);
		} while (!I.equals(big1));  
		System.out.println();
		
	} 

}
