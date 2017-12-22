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

public class Ex7Dequeue<Item> implements Iterable<Item> {
	private Node<Item> first;
	private Node<Item> last;
	private int size;
	
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> previous;
	}
	
	public Ex7Dequeue() {
		first = null;
		last = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return size;
	}
	
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
	
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
	
	public static void main(String[] args) {
		
		Ex7Dequeue<Integer> q = new Ex7Dequeue<>();
		
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
