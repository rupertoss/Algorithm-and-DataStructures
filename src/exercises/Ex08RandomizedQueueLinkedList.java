package exercises;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

public class Ex08RandomizedQueueLinkedList<Item> {

	private Node<Item> first;
	private int size;

	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}

	public Ex08RandomizedQueueLinkedList() {
		first = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void enqueue(Item item) {
		if(item == null) 
			throw new IllegalArgumentException();
		Node<Item> oldFirst = first;
		first = new Node<>();
		first.next = oldFirst;
		first.item = item;
	}

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
		return toDelete.item;
	}
	
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
