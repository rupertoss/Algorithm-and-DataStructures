/**
 * 3-SUM in quadratic time. 
 * 
 * Design an algorithm for the 3-SUM problem that takes time 
 * proportional to n2 in the worst case. 
 * You may assume that you can sort the n integers in time proportional to n2 or better.
 * 
 * The solution is based on 2SUM algorithm.
 */

package exercises;

import java.util.ArrayList;
import java.util.Arrays;

public class Ex10ThreeSum {
	ArrayList<String> results;
	
	public Ex10ThreeSum (int[] data) {
		
		Arrays.sort(data);
		results = new ArrayList<>();
		
		for (int i=0; i < data.length; i++) {
			int firstPointer = i + 1;
			int secondPointer = data.length - 1;
			
			while(firstPointer < secondPointer) {
				int sum = data[i] + data[firstPointer] + data[secondPointer];
				if(sum < secondPointer) {
					firstPointer++;
				} else if (sum > 0) {
					secondPointer--;
				} else {
					results.add(data[i] + " " + data[firstPointer] + " " + data[secondPointer]);
				}
			}
		}
	}
}

