package exercises;

import java.util.ArrayList;
import java.util.Collections;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Solution is based on divide and conquer method.
 */
public class Ex21SelectionInTwoSortedArrays {
	
	private Integer[] a1;
	private Integer[] a2;

	public Ex21SelectionInTwoSortedArrays(int size1, int size2, int k) {
		
		a1 = new Integer[size1];
		a2 = new Integer[size2];
		
		ArrayList<Integer> array1 = new ArrayList<>();
		ArrayList<Integer> array2 = new ArrayList<>();
		
		for(int i = 0; i < size1; i++) {
			int random = StdRandom.uniform(20);
			while(array1.contains(random)) {
				random = StdRandom.uniform(20);
			}
			array1.add(random);
		}
		
		for(int i = 0; i < size2; i++) {
			int random = StdRandom.uniform(20);
			while(array2.contains(random)) {
				random = StdRandom.uniform(20);
			}
			array2.add(random);
		}
		
		Collections.sort(array1);
		Collections.sort(array2);
		
		int result = findK(array1.toArray(a1), array2.toArray(a2), 0, size1 - 1, 0, size2 - 1, k);
		
		System.out.println(result);
		System.out.println(array1);
		System.out.println(array2);
	}
	
	
	private int findK(Integer[] a1, Integer[] a2, int start1, int end1, int start2, int end2, int k) {
		
		if(k - end2 + start2 == 0)
			return a1[start1];
		if(k - end1 + start1 == 0)
			return a2[start2];
		
		int mid1 = start1 + k/2;
		int mid2 = start2 + k/2;
		
		if(a1[mid1] > a2[mid2]) {
			return findK(a1, a2, start1, mid1, mid2, end2, k/2);
		} else if(a1[mid1] < a2[mid2]) {
			return findK(a1, a2, mid1, end1, start2, mid2, k/2);
		} else
			return a1[mid1];
	}
	
	// for testing
	public static void main(String[] args) {
		new Ex21SelectionInTwoSortedArrays(5, 6, 1);
	}
}
