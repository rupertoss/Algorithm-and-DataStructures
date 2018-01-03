package exercises;

import edu.princeton.cs.algs4.StdIn;

public class Ex09PermutationClient {

	Ex08RandomizedQueueLinkedList<String> rqll;
	
	public Ex09PermutationClient(int number) {
		rqll = new Ex08RandomizedQueueLinkedList<>();
		
		//reading the input
		while(!StdIn.isEmpty()) {
			rqll.enqueue(StdIn.readString());
		}
		
		//printing number of elements randomly
		while(number > 0) {
			System.out.println(rqll.dequeue());
			number--;
		}
	}
}
