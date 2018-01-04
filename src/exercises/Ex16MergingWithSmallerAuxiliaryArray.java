package exercises;


public class Ex16MergingWithSmallerAuxiliaryArray {
	/**
	 * Merges two sorted subarrays of array {@code a} 
	 * with auxiliary array half of the size of array {@code a}.
	 *  
	 * @param a an array with two equal size sorted subarrays
	 */
	public static void merge(int[] a) {

		int lo = 0;
		int hi = a.length;
		int mid = hi/2;
		
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
		
		// create auxiliary array half size of a
		int[] aux = new int[mid];
		
        // copy first subarray to aux[]
        for (int k = lo; k < mid; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
        	if (i > mid)					return;
        	else if (j > hi)				a[k] = aux[i++];
            else if (aux[j] < aux[i])		a[k] = a[j++];
            else                          	a[k] = aux[i++];
        }
    }
}
