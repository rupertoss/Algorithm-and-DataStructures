/**
 * Dequeue. 
 * 
 * A double-ended queue or deque (pronounced “deck”) is a generalization 
 * of a stack and a queue that supports adding and removing items 
 * from either the front or the back of the data structure.
 */

package exercises;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Ex07Dequeue<Item> implements Iterable<Item> {
	private Node<Item> first;
	private Node<Item> last;
	private int size;
	
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> previous;
	}
	
	/**
	 * Initializes empty dequeue
	 */
	public Ex07Dequeue() {
		first = null;
		last = null;
		size = 0;
	}
	
	/**
	 * Returns true if dequeue is empty
	 * 
	 * @return true if dequeue is empty, false otherwise
	 */
	public boolean isEmpty() {
		return first == null;
	}
	
	/**
	 * Returns the number of items in dequeue
	 * 
	 * @return the number of items in dequeue
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Add the item to the beginning of dequeue
	 * 
	 * @param item adding to dequeue
	 * @throws IllegalArgumentException if {@code item} is null
	 */
	public void addFirst(Item item) {
		if(item == null) 
			 throw new IllegalArgumentException();
		Node<Item> oldFirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldFirst;
		if(size != 0) {
			oldFirst.previous = first;
		} else {
			last = first;
		}
		size++;
	}
	
	/**
	 * Add the item to the end of dequeue
	 * 
	 * @param item adding to dequeue
	 * @throws IllegalArgumentException if {@code item} is null
	 */
	public void addLast(Item item) {
		if(item == null) {
			 throw new IllegalArgumentException();
		}
		Node<Item> oldLast = last;
		last = new Node<Item>();
		last.item = item;
		last.previous = oldLast;
		if(size != 0) {
			oldLast.next = last;
		} else {
			first = last;
		}
		size++;
	}

	/**
	 * Removes and returns the item from the beginning of dequeue
	 *  
	 * @return the item removed from the beginning of dequeue
	 * @throws NoSuchElementException if this stack is empty
	 */
	public Item removeFirst() {
		if(size == 0)
			throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		if(size != 1) {
			first.previous = null;
		} else {
			last = null;
		}
		size--;
		return item;
	}
	
	/**
	 * Removes and returns the item from the end of dequeue
	 *  
	 * @return the item removed from the end of dequeue
	 * @throws NoSuchElementException if this stack is empty
	 */
	public Item removeLast() {
		if(size == 0)
			throw new NoSuchElementException();
		Item item = last.item;
		last = last.previous;
		if(size != 1) {
			last.next = null;
		} else {
			first = null;
		}
		size--;
		return item;
	}
	
    /**
     * Returns an iterator to this dequeue that iterates through the items from beginning to end
     *
     * @return an iterator to this dequeue that iterates through the items from beginning to end
     */
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}
	
	private class ListIterator<Item> implements Iterator<Item> {
		private Node<Item> current;
		
		public ListIterator(Node<Item> first) {
			current = first;
		}
		
		public boolean hasNext() {
			return current != null;
		}
		
        public void remove() {
            throw new UnsupportedOperationException();
        }
		
		public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	
    /**
     * Returns a string representation of this dequeue
     *
     * @return the sequence of items in this dequeue from beginning to end, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
	
	public static void main(String[] args) {
		
		Ex07Dequeue<Integer> q = new Ex07Dequeue<>();
		
		int i = 1;
		int N = 20;
		
	    System.out.println("Size: " + q.size());
	    while (i <= N) {
	      System.out.println("-------------------");
	      System.out.println(q.toString() + " and the size is: " + q.size());

	      if (i % 4 == 0) {
	        System.out.println("removedFirst: " + q.removeFirst());
	      } else if(i % 5 == 0) {
	    	  System.out.println("addedLast: " + i);
	    	  q.addLast(i);
	      } else if(i % 7 == 0) {
	    	  System.out.println("removedLast: " + q.removeLast());
	      } else {
	        q.addFirst(i);
	  	  	System.out.println("addedFirst: " + i);
	      }
	      System.out.println(q.toString() + " and the size is: " + q.size());
	      i++;
	    }
	    System.out.println("Size: " + q.size());
	    while (!q.isEmpty()) {
		      System.out.println("-------------------");
		      System.out.println(q.toString() + " and the size is: " + q.size());

	        System.out.println("removedLast: " + q.removeLast());

		      System.out.println(q.toString() + " and the size is: " + q.size());
	    }
	    System.out.println("Size: " + q.size());
	    
	    System.out.println("\n\n\nSecondrun\n\n\n");
		i = 1;
		N = 20;
		
	    System.out.println("Size: " + q.size());
	    while (i <= N) {
	      System.out.println("-------------------");
	      System.out.println(q.toString() + " and the size is: " + q.size());

	      if (i % 4 == 0) {
	        System.out.println("removedFirst: " + q.removeFirst());
	      } else if(i % 5 == 0) {
	    	  System.out.println("addedLast: " + i);
	    	  q.addLast(i);
	      } else if(i % 7 == 0) {
	    	  System.out.println("removedLast: " + q.removeLast());
	      } else {
	        q.addFirst(i);
	  	  	System.out.println("addedFirst: " + i);
	      }
	      System.out.println(q.toString() + " and the size is: " + q.size());
	      i++;
	    }
	    System.out.println("Size: " + q.size());
	    while (!q.isEmpty()) {
		      System.out.println("-------------------");
		      System.out.println(q.toString() + " and the size is: " + q.size());

	        System.out.println("removedLast: " + q.removeLast());

		      System.out.println(q.toString() + " and the size is: " + q.size());
	    }
	    System.out.println("Size: " + q.size());
	}
	
}
