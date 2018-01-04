package exercises;

public class Ex17CountingInversions {
	
	private static int inversions;
	
	// stably merge a[lo .. mid] with a[mid+1 ..hi]
	private static void merge(int[] a, int lo, int mid, int hi) {

		// create auxiliary array
		int[] aux = new int[hi];
		
        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)           	a[k] = aux[j++];
            else if (j > hi)           	   {a[k] = aux[i++]; inversions += (hi - mid);}
            else if (aux[j] < aux[i]) 	   {a[k] = aux[j++]; inversions += (mid - i);}
            else                           {a[k] = aux[i++]; inversions += (j - mid);}
        }
        
        // avoid loitering
        aux = null;
    }
	
    // mergesort a[lo..hi]
    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }
    
    /**
     * Returns number of inversions and rearranges the array in ascending order.
     * @param a the array to be sorted and inversion counted
     * @return the number of inversions in given array
     */
    public static int inversionCount(int[] a) {
    	inversions = 0;
        sort(a, 0, a.length-1);
        return inversions;
    }
}
