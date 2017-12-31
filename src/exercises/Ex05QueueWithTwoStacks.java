
package exercises;

import edu.princeton.cs.algs4.Stack;

public class Ex05QueueWithTwoStacks {
	
	
	private Stack<Integer> sIn = new Stack<>();		// stack containing input elements
	private Stack<Integer> sOut = new Stack<>();	// stack containing output elements
	
	/**
	 * Adding {@code number} to queue
	 * 
	 * @param number integer representing adding number
	 */
	public void push(int number) {
		if(!sOut.isEmpty()) {
			for(int i: sOut) {
				sIn.push(i);
			}
			sOut = new Stack<>();
		}
		sIn.push(number);
	}
	
	/**
	 * Removes and returns the last added number to queue
	 * 
	 * @return integer representing last added number
	 */
	public int pop() {
		if(!sIn.isEmpty()) {
			for(int i: sIn) {
				sOut.push(i);
			}
			sIn = new Stack<>();
		}
		return sOut.pop();
	}
	
	/**
	 * Returns true if queue is empty
	 * 
	 * @return true if queue is empty, false otherwise
	 */
	public boolean isEmpty() {
		return sIn.isEmpty() && sOut.isEmpty();
	}
	
	/**
	 * Returns the integer representing the size of the queue
	 * 
	 * @return the integer representing the size of the queue
	 */
	public int size() {
		return sIn.size() + sOut.size();
	}
	
	// for testing
	public static void main(String[] args) {
		
		Ex05QueueWithTwoStacks queue = new Ex05QueueWithTwoStacks();
	    int i = 0;
	    int N = 150;

	    System.out.println("Size: " + queue.size());
	    queue.push(i);
	    while (i <= N) {
	      if (i % 4 == 0) {
	        System.out.println("sIn: " + queue.pop());
	      } else {
	        queue.push(i);
	        System.out.println("sOut: " + i);
	      }
	      i++;
	    }
	    System.out.println("Size: " + queue.size());
	    while (!queue.isEmpty()) {
	        System.out.println("sOut: " + queue.pop());
	    }
	    System.out.println("Size: " + queue.size());
	}
}
