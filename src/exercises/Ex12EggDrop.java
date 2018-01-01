package exercises;

import java.util.concurrent.ThreadLocalRandom;

public class Ex12EggDrop {
	
	private int floors;	// number of floors in building
	private int limit;	// floor when eggs breaks
	private int eggs;	// number of eggs broken
	private int tosses;	// number of tosses
	
	public Ex12EggDrop(int f) {
		floors = f;
		
		limit = ThreadLocalRandom.current().nextInt(1, floors + 1);
		
		System.out.println("The eggs will break at: " + limit);
		
		//version 0: 1 egg, tosses <= @limit
		//sequential search
		eggs = 0;
		tosses = 0;
		
		for(int i = 0; i < floors; i++) {
			tosses++;
			if(i >= limit) {
				eggs++;
				break;
			}
		}
		System.out.println("Version 0");
		System.out.println("Eggs used: " + eggs);
		System.out.println("Tosses: " + tosses);
		
		//version 1: ~1lg @floors eggs, ~1lg @floors tosses
		//binary search
		eggs = 0;
		tosses = 0;
		
        int lo = 0;
        int hi = floors;
        while (lo <= hi) {
        	tosses++;
            int mid = lo + (hi - lo) / 2;
            if (limit < mid) { 
            	hi = mid - 1;
            	eggs++;
            }
            else if (limit > mid) 
            	lo = mid + 1;
            else {
            	eggs++;
            	break;
            }
        }
		System.out.println("Version 1");
		System.out.println("Eggs used: " + eggs);
		System.out.println("Tosses: " + tosses);
		
		//version 2: ~lg @limit eggs, ~2lg @limit tosses
		//go for floor 1>2>4>8... and then binary search
		eggs = 0;
		tosses = 0;
		
		for(int i = 1; i < 2*floors; i *= 2) {
			tosses++;
			if(i-1 > limit) {
				eggs++;
			    lo = tosses/2;
		        hi = floors;
		        while (lo <= hi) {
		        	tosses++;
		            int mid = lo + (hi - lo) / 2;
		            if (limit < mid) { 
		            	hi = mid - 1;
		            	eggs++;
		            }
		            else if (limit > mid) 
		            	lo = mid + 1;
		            else {
		            	eggs++;
		            	break;
		            }
		        }
				break;
			}
		}

		System.out.println("Version 2");
		System.out.println("Eggs used: " + eggs);
		System.out.println("Tosses: " + tosses);
		
		//version 3: 2 eggs, 2sqrt(@floors) tosses
		//divide into pieces containing sqrt(@floors) and then sequential search
		eggs = 0;
		tosses = 0;
		
		for(int i = (int)Math.sqrt(floors); i <= floors; i += Math.sqrt(floors)) {
			tosses++;
			if(i >= limit) {
				eggs++;
				i -= Math.sqrt(floors);
				while(i < limit) {
					tosses++;
					i++;
				}
				eggs++;
				break;
			}
		}
		System.out.println("Version 3");
		System.out.println("Eggs used: " + eggs);
		System.out.println("Tosses: " + tosses);
		
		//version 4: 2 eggs, c*sqrt(@limit) tosses for some fixed constant c
		//1>2>3>4... intervals and the sequential search
		
		eggs = 0;
		tosses = 0;
		int interval = 0;
		for(int i = 0; i < floors; i += interval) {
			interval++;
			tosses++;
			if(i >= limit) {
				eggs++;
				i -= interval;
				while(i < limit) {
					tosses++;
					i++;
				}
				eggs++;
				break;
			}
		}
		System.out.println("Version 4");
		System.out.println("Eggs used: " + eggs);
		System.out.println("Tosses: " + tosses);
		
	}
	
	public static void main(String[] args) {
		Ex12EggDrop egg = new Ex12EggDrop(10000);
	}
}
