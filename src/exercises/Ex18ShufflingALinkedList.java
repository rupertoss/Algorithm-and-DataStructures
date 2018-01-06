package exercises;

import java.util.LinkedList;

import edu.princeton.cs.algs4.StdRandom;

public class Ex18ShufflingALinkedList {
	/**
	 * Shuffle uniformly given list.
	 * It iterates through all elements and by random moving to pointer position.
	 * Then divides the list into two halves and iterates again then repeats whole process. 
	 * It should store reference to the element with the pointer index.
	 * Runs in N*long(N) in the worst case.
	 *  
	 * @param list
	 */
	public static void shuffle(LinkedList<Integer> list) {
		for(int k = list.size(); k > 1; k /= 2) {		// log(n) runs
			for (int i = 0; i < list.size(); i++) {		// n runs
				int pointer = 0;						// pointer (first element in array/subarray)
				if(i > pointer + k)						// move the pointer if in next subarray
					pointer += k;
				if(StdRandom.uniform(2) > 0) {
					Integer tmp = list.get(i);
					list.remove(i);						// remove element
					list.add(pointer, tmp);				// add in the pointer position
				}
			}
		}
	}

	// unit testing
	public static void main(String[] args) {
		
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < 20; i++) {
			list.add(i);
		}
		
		Ex18ShufflingALinkedList.shuffle(list);
		list.forEach(System.out::println);
	}
}
