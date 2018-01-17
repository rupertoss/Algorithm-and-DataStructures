package exercises;

import java.util.ArrayList;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Solution is based on quicksort algorithm.
 */
public class Ex20NutsAndBolts {
	
	private Integer[] bolts;
	private Integer[] nuts;

	public Ex20NutsAndBolts(int number) {
		
		// creation of randomly ordered arrays
		bolts = new Integer[number];
		nuts = new Integer[number];
		
		ArrayList<Integer> boltsList = new ArrayList<>();
		ArrayList<Integer> nutsList = new ArrayList<>();
		
		for(int i = 0; i < number; i++) {
			int random = StdRandom.uniform(number);
			while(boltsList.contains(random)) {
				random = StdRandom.uniform(number);
			}
			boltsList.add(random);
		}
		
		for(int i = 0; i < number; i++) {
			int random = StdRandom.uniform(number);
			while(nutsList.contains(random)) {
				random = StdRandom.uniform(number);
			}
			nutsList.add(random);
		}
		
		// perform sorting
		sort(boltsList.toArray(bolts), nutsList.toArray(nuts));
		
		// print results of sorting
		for(int i = 0; i < number; i++) {
			System.out.println(bolts[i] + " " + nuts[i]);
		}
	}
	
	
	private static void sort(Integer[] bolts, Integer[] nuts) {
		sort(bolts, nuts, 0, bolts.length - 1);
	}
	
	private static void sort(Integer[] bolts, Integer[] nuts, int low, int high) {
		if(low < high) {
			int pivot = partition(bolts[low], nuts, low, high);
			
			partition(pivot, bolts, low, high);
			
			sort(bolts, nuts, low, pivot - 1);		// sort recursively first part
			sort(bolts, nuts, pivot + 1, high);		// sort recursively second part
		}
	}
	
    private static int partition(Integer pivot, Integer[] array, int low, int high) {
    	
    	int counter = low;	// how many smaller than pivot
    	int tmp;			// for swapping
    	int pointer = 0;	// position of equal element to pivot 
    	
    	for(int k = low; k <= high; k++) {
    		if(array[k] < pivot) {
    			counter++;
    		} else if(array[k] > pivot) {
    			tmp = array[k];
    			array[k] = array[high];
    			array[high] = tmp;
    			k--;
    			high--;
    		} else {
    			pointer = k;
    		}
    	}
    	
    	// swapping element equal to pivot
    	tmp = array[counter];		
    	array[counter] = array[pointer];
    	array[pointer] = tmp;
    	
    	return counter;	// returning index of element equal to pivot
    }
    
    public static void main(String[] args) {
    	new Ex20NutsAndBolts(15);
	}
}
