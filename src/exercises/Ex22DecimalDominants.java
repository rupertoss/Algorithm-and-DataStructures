package exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.StdRandom;

public class Ex22DecimalDominants {
	
	public static void n10(int n) {
		List<Integer> array = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		Set<Integer> n10 = new HashSet<>();
		
		// random input creation
		for(int i = 0; i < n; i++) {
			int random = StdRandom.uniform(20);
			array.add(random);
		}
		
		
		for(int entry: array) {						// for each key
			if(!map.containsKey(entry)) {			// if not seen yet, add to map
				map.put(entry, 1);					
			} else {
				map.put(entry, map.get(entry) + 1);	// if seen, put with value one more than earlier
				if(map.get(entry) == n/10) {		// if n/10 add to set
					n10.add(entry);
				}
			}
		}
		
		Collections.sort(array);
		System.out.println(array);
		System.out.println("======= " + n/10 + " =======");
		System.out.println(n10);
	}
	
	// testing
	public static void main(String[] args) {
		Ex22DecimalDominants.n10(50);
	}
}
