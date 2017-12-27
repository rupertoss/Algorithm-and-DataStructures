/**
 * Search in a bitonic array. 
 * 
 * An array is bitonic if it is comprised of an increasing sequence 
 * of integers followed immediately by a decreasing sequence of integers. 
 * Write a program that, given a bitonic array of n distinct integer values, 
 * determines whether a given integer is in the array.
 * 
 * Standard version: Use ~3lgn compares in the worst case.
 * Signing bonus: Use ~2lgn compares in the worst case (and prove that no algorithm 
 * can guarantee to perform fewer than ~2lgn compares in the worst case).
 */

package exercises;

import java.util.Arrays;

public class Ex11SearchInBitonicArray {
	
	int index; // index of element in array

	/**
	 * Returns the index of the demanded element {@code number} in the bitonic array {@code array} if existing.
	 * Otherwise return -1;
	 * 
	 * @param array in which searching will be processed
	 * @param number the integer representing demanded element
	 */
	public Ex11SearchInBitonicArray(int[] array, int number) {
		int length = array.length;				// length of given array
		int midValue = array[length/2];			// value of element with the middle index
		int leftNeighbour = array[length/2 -1];	// value of left neighbour of middle
		
		if(midValue < number) {
			if(midValue < leftNeighbour) { //max on the left side of array
				//search the left side
				this.index = Arrays.binarySearch(array, 0, midValue, number);
			} else if (midValue > leftNeighbour){
				//search the right side
				this.index = Arrays.binarySearch(array, midValue, length, number);
			} else {
				//number == leftNeighbour
				this.index = length/2 -1;
			}
		} else if(midValue > number) {	//max on the right side of array
			//asc binary search on the left
			this.index = Arrays.binarySearch(array, 0, midValue, number);
			
			//desc binary search on the right
		    for(int i = 0; i < array.length / 2; i++){
		        int temp = array[i]; // swap using temporary storage
		        array[i] = array[array.length - i - 1];
		        array[array.length - i - 1] = temp;
		    }
		    this.index = length - 1 - Arrays.binarySearch(array, 0, midValue, number);
		} else {
			//number == midValue;
			this.index = length/2;
		}
	}
}
