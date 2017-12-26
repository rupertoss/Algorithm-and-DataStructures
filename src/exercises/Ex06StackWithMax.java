/**
 * Stack with max. 
 * 
 * Create a data structure that efficiently supports the stack 
 * operations (push and pop) and also a return-the-maximum operation. 
 * Assume the elements are reals numbers so that you can compare them.
 */

package exercises;

public class Ex06StackWithMax {
	
	private Node first = null;
	private Node max = null;

	class Node {
		int number;
		Node next;
		Node nextMaximumNode;
	}
	
	/**
	 * Return true if stack is empty
	 * 
	 * @return true if stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		return first == null;
	}
	
	/**
	 * Adds the {@code number} to stack
	 * 
	 * @param number integer representing value
	 */
	public void push (int number) {
		
		Node oldFirst = first;
		first = new Node();
		first.number = number;
		first.next = oldFirst;
		
		Node oldMax = max;
		if (max == null) {
			max = new Node();
			max.number = number;
			max.nextMaximumNode = oldMax;	
		} else if(number >= oldMax.number) {  
			max = new Node();
			max.number = number;
			max.nextMaximumNode = oldMax;
		}
	}
	
	/**
	 * Removes and returns the most recently added value 
	 * 
	 * @return integer representing popped value
	 */
	public int pop() {
		int item = first.number;
		if (first.number == max.number)
			max = max.nextMaximumNode;
		first = first.next;
		return item;
	}
	
	/**
	 * Returns maximum value in stack
	 * 
	 * @return integer representing maximum value
	 */
	public int maximum() {
		return max.number;
	}
	
}
