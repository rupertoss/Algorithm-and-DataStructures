package exercises;

public class Ex15DutchNationalFlag {
	
	/**
	 * Sort {@code buckets} array containing pebbles of colours
	 * red ('r'), white ('w'), blue ('b') in that order.
	 * Runs with at most linear calls to color() and swap(). 
	 * 
	 * @param buckets array to sort
	 * @return sorted array in 'r'>'w'>'b' order
	 */
	public static char[] sort (char[] buckets) {
		
		int redPointer = 0;
		int currentPointer = 0;
		int bluePointer = buckets.length - 1;
		
		while(currentPointer < bluePointer) {
			if(colour(buckets, currentPointer) == 'r') {
				swap(buckets, currentPointer, redPointer);
				redPointer++;
			}
			
			if (colour(buckets, currentPointer) == 'b') {
				swap(buckets, currentPointer, bluePointer);
				bluePointer--;
			}
			currentPointer++;
		}
		return buckets;
	}
	
	/**
	 * Returns the colour of pebble in bucket with given index.
	 * It consider that bucket array contains chars 'r', 'w' or 'b'
	 * and all parameter are not null.
	 * 
	 * @param array bucket array of pebbles
	 * @param index integer representing index of pebble in bucket array
	 * @return the colour of pebble in bucket with given index
	 */
	private static char colour(char[] array, int index) {
		return array[index];
	}
	
	/**
	 * Swaps two elements with each other in given array
	 * It consider that all parameters are not null
	 * and addition index1 and index2 are smaller than lengts of an array.
	 * 
	 * @param array containing elements to swap
	 * @param index1 of first element 
	 * @param index2 of second element
	 */
	private static void swap(char[] array, int index1, int index2) {
		char tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
	}
}
