package exercises;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Linked List implementation with removing and iterating elements by random
 * Adding element runs in constant time.
 * Iterating and removing element runs in subquadratic time.
 * 
 * @author rupertoss
 */
public class Ex08RandomizedQueueLinkedList<Item> {

	private Node<Item> first;
	private int size;

	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}
	
	/**
	 * Constructs empty RQLL with {@code size} set to 0
	 */
	public Ex08RandomizedQueueLinkedList() {
		first = null;
		size = 0;
	}

	/**
	 * Returns true if RQLL is empty
	 * 
	 * @return true when {@code size} is zero, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns number of elements in RQLL
	 * 
	 * @return the integer representing number of elements in RQLL
	 */
	public int size() {
		return size;
	}

	/**
	 * Adds the {@code item} at the front of the RQLL
	 * 
	 * @param item adding to RQLL
	 * @throws IllegalArgumentException when {@code item} is null
	 */
	public void enqueue(Item item) {
		if(item == null) 
			throw new IllegalArgumentException();
		Node<Item> oldFirst = first;
		first = new Node<>();
		first.next = oldFirst;
		first.item = item;
	}

	/**
	 * Removes random element from RQLL and returns {@code item} property of that element
	 * 
	 * @return {@code item} property of removed element
	 */
	public Item dequeue() {
		if(isEmpty())
			throw new NoSuchElementException();
		int index = ThreadLocalRandom.current().nextInt(1, size);
		Node<Item> toDelete = new Node<>();
		if (index == 1) {
			toDelete = first;
			if(size != 1)
				first = first.next;
			else
				first = null;
		} else {
			Node<Item> helper = first;
			for (int i = 1; i < index - 1; i++) {
				helper = helper.next;
			}
			toDelete = helper.next;
			if(index == size)
				helper.next = null;
			else
				helper.next = helper.next.next;
		}
		size--;
		return toDelete.item;
	}
	
	/**
	 * Returns {@code item} property of random element
	 * 
	 * @return {@code item} property of random element
	 */
	public Item sample() {
		if(isEmpty())
			throw new NoSuchElementException();
		int index = ThreadLocalRandom.current().nextInt(1, size);
		Node<Item> sample = first;
		for(int i = 1; i < index; i++) {
			sample = sample.next;
		}
		return sample.item;
	}
	
	/**
	 * Returns an iterator to this RQLL that iterates through elements by random
	 * 
	 * @return an iterator to this RQLL that iterates through elements by random
	 */
	public Iterator<Item> iterator() {
		return new RandomIterator<Item>(first);
	}
	
	private class RandomIterator<Type> implements Iterator<Type> {
		private Node<Type> first;
		private Node<Type> current;
		private int[] sequence = new int[size];
		private int index = 0;
		
		public RandomIterator(Node<Type> first) {
			for(int i = 0; i < size; i++) {
				int random = ThreadLocalRandom.current().nextInt(0, size - i);
				sequence[i] = random;
			}
			this.first = first;
			current = get(sequence[index]);
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Type next() {
			if(!hasNext())
				throw new NoSuchElementException();
			Type item = current.item;
			current = get(sequence[++index]);
			return item;
		}
		
		@Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
		
		public Node<Type> get(int position) {
			current = this.first;
			for(int i = 0; i < position; i++) {
				current = current.next;
			}
			return current;
		}
	}
	
}
