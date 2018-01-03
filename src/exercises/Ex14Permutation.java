package exercises;

import edu.princeton.cs.algs4.Shell;

public class Ex14Permutation {
	
	/**
	 * Returns true if one array is permutation of another.
	 * It uses shell sort as subquadratic sorting algorithm to sort arrays
	 * and then iterates through elements to check if are the same.
	 * 
	 * @param arrayA first array
	 * @param arrayB second array
	 * @return true if one array is permutation of another, false otherwise
	 * @throws IllegalArgumentException if any of arrays are null
	 */
	public static boolean isPermutation(Integer[] arrayA, Integer[] arrayB) {
		
		if(arrayA == null || arrayB == null)
			throw new IllegalArgumentException();
		
		if(arrayA.length != arrayB.length) {
			return false;
		}
		
		Shell.sort(arrayA);
		Shell.sort(arrayB);
		
		for(int i = 0; i < arrayA.length; i++) {
			if(arrayA[i] != arrayB[i]) {
				return false;
			}
		}
		
		return true;
	}

}
