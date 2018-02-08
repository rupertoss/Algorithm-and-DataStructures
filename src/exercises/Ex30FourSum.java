package exercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.algs4.StdRandom;

public class Ex30FourSum {
	
	public static List<int[]> fourSum(int size, int range) {

		List<int[]> results = new ArrayList<>(); 
		
		// creating random input
		List<Integer> helper = new ArrayList<>(); 
		for(int i = 0; i < size; i++) {
			int random = StdRandom.uniform(range);
			while(helper.contains(random)) {
				random = StdRandom.uniform(range);
			}
			helper.add(random);
		}
		
		Integer[] numbers = helper.toArray(new Integer[helper.size()]);
		
		Map<Integer, int[]> sumOfTwoElements = new HashMap<>();
		
		// computing sum of two elements
		for(int i = 0; i < numbers.length; i++) {
			for(int j = 0; j < numbers.length - i; j++) {
				sumOfTwoElements.put(i+j, new int[]{i,j});
			}
		}
		
		// checking for sum and -sum
		for(Integer sum: sumOfTwoElements.keySet()) {
			if(sumOfTwoElements.containsKey(-sum)) {
				int[] ab = sumOfTwoElements.get(sum);
				int[] cd = sumOfTwoElements.get(-sum);
				
				results.add(new int[] {ab[0], ab[1], cd[0], cd[1]});
			}
		}
		
		return results;
	}

}
