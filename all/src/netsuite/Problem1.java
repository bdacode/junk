package netsuite;

import java.util.Arrays;
import java.util.Iterator;

public class Problem1 {

	/**
	 * When I read the problem A first, I thought you want a new array to be allocated 
	 */
    static int[] removeDuplicates(int[] values)
    {	
    	if (null == values) {
    		return null;
    	}
        int [] result = new int[values.length];
        int count = 0;
        for (int i=0;i<values.length;i++) {
        	result[count] = values[i];
        	if (i == 0 || result[count-1] != result[count]) {
        		count++;
        	}
        }
        
        return Arrays.copyOf(result, count);
    }

    /**
     * After I read your problem B, it seems that you want me to modify the input array
     * directly, so now I give you the implementation that does that.
     */
    static int[] removeDuplicatesWithoutNewArray(int[] values)
    {	
    	if (null == values) {
    		return null;
    	}
        int insertLocation = 0;
        for (int i=0;i<values.length;i++) {
        	values[insertLocation] = values[i];
        	if (i == 0 || values[insertLocation-1] != values[insertLocation]) {
        		insertLocation++;
        	}
        }
        while (insertLocation < values.length) {
        	values[insertLocation++] = 0;
        }
        return values;
    }

    
    static class UniqueIterator<E> implements Iterator<E> {
    	
    	E[] array;
    	int currentIndex;

    	UniqueIterator(E[] array) {
    		this.array = array;
    	}

		@Override
		public boolean hasNext() {			
			return currentIndex < array.length;
		}

		@Override
		public E next() {
			E result = array[currentIndex];
			while (currentIndex < array.length) {
				if (result == null && array[currentIndex] != null) {
					break;
				} else if (result != null && !result.equals(array[currentIndex])) {
					break;
				}
				currentIndex++;
			}
			return result;
		}

		@Override
		public void remove() {
			throw new RuntimeException("Unimplemented function");
		}
    	
    }
    public static void main(String[] args) {
		print(removeDuplicatesWithoutNewArray(new int[] {}));
		print(removeDuplicatesWithoutNewArray(new int[] {1}));
		print(removeDuplicates(new int[] {1, 2, 3, 3, 3, 4, 4, 10, 13, 15, 15, 17}));
		print(removeDuplicatesWithoutNewArray(new int[] {1, 2, 3, 3, 3, 4, 4, 10, 13, 15, 15, 17}));
		print(removeDuplicatesWithoutNewArray(new int[] {1, 1}));
		
		UniqueIterator<Integer> ui = new UniqueIterator<Integer>(new Integer[] {1, 2, 3, 3, 3, 4, 4, 10, 13, 15, 15, 17});
		while (ui.hasNext())
			System.out.print(ui.next()+" ");
		System.out.println();
	}

	private static void print(int[] removeDuplicates) {
		for (int i : removeDuplicates) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
    
}
