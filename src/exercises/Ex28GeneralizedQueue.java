package exercises;

import edu.princeton.cs.algs4.BST;

public class Ex28GeneralizedQueue<Value> extends BST<Integer, Value> {

	// creates empty data structure
	public Ex28GeneralizedQueue() {
		super();
	}
	
	// appends an item to the end
	public void enqueue(Value item) {
		this.put(this.size()+1, item);
	}
	
	// deletes first item
	public void dequeue() {
		this.deleteMin();
	}
	
	// returns i-th item
	public Value getIthValue(Integer i) {
		int max = this.ceiling(new Integer(Integer.MAX_VALUE));
		int key = max - this.size() + i;
		return this.get(key);
	}
	
	// deletes i-th item
	public void deleteIthValue(Integer i) {
		int max = this.ceiling(new Integer(Integer.MAX_VALUE));
		int key = max - this.size() + i;
		this.delete(key);
	}
}
