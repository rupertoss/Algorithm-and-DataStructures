package exercises;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import edu.princeton.cs.algs4.StdRandom;

public class Ex23DynamicMedian {
	
	// reverse comparator
	private static class ReverseComparator<T> implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			if(o1>o2)
				return -1;
			if(o1<o2)
				return 1;
			return 0;
		}
	}
	
	// perform calculation
	public static double calculateMedian(Integer[] array) {
		PriorityQueue<Integer> smaller = new PriorityQueue<>(new ReverseComparator<Integer>());		// heap with values smaller than median
		PriorityQueue<Integer> bigger = new PriorityQueue<>();		// heap with values bigger than median
		
		for(int i = 0; i < array.length; i++) {
			
			if(array[i] > getMedian(smaller, bigger)) {  // check if current element is bigger or smaller than current median
				bigger.add(array[i]);	// element bigger >> add to bigger heap
			} else {	
				smaller.add(array[i]);	// element smaller >> add to smaller heap
			}
			balance(smaller, bigger);	// balance size of heaps
		}
		return getMedian(smaller, bigger);
	}
	
	// balance heaps if one is bigger than other
	private static void balance(PriorityQueue<Integer> pq1, PriorityQueue<Integer> pq2) {
		if(pq1.size() > pq2.size() +1) {
			pq2.add(pq1.poll());
		} else if(pq1.size() +1 < pq2.size()){
			pq1.add(pq2.poll());
		} else {
		return;
		}
	}
	
	// returns current median of two heaps
	private static double getMedian(PriorityQueue<Integer> pq1, PriorityQueue<Integer> pq2) {
		if(pq1.isEmpty() && pq2.isEmpty())
			return 0;
		if(pq1.size() > pq2.size())
			return pq1.peek();
		if(pq1.size() < pq2.size())
			return pq2.peek();
		return (double)(pq1.peek() + pq2.peek()) / 2;
	}
	
	// for testing
	public static void main(String[] args) {
		int size = 101;
		int range = 100;
		Integer[] array = new Integer[size];
		for(int i = 0; i < size; i++) {
			array[i] = StdRandom.uniform(range) + 1;
		}
		System.out.println(Ex23DynamicMedian.calculateMedian(array));
		Arrays.sort(array, new Ex23DynamicMedian.ReverseComparator<Integer>());
		System.out.println(array[50]);
	}
}
