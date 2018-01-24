package exercises;

import java.util.ArrayList;
import java.util.List;

public class Ex24TaxicabNumbers {
	
	// inner class Taxicab
	static class Taxicab {
		private int N;
		private int a,b,c,d;
		
		public Taxicab(int n, int a, int b, int c, int d) {
			N = n;
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Taxicab [N=" + N + " | a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + "]";
		}
	}
	
	public static void taxicab(int N) {
		List<Taxicab> taxicabs = new ArrayList<>();		// list of taxicabs smaller than N
		
		List<Integer> abcd;								// helper list
		
		for(int i = 1; i <= N; i++) {					// run for each number of N
			
			abcd = new ArrayList<>();					// for each number create an empty list

			for(int j = 1; j <= i; j++) {				// search for cubes for each N
				
				int a = (int)Math.round(Math.pow(i-j*j*j, (double)1/3));		// rounded cube root of N minus cube of j
				
				if(a*a*a == i-j*j*j && !abcd.contains(a)) {		// if cube of a equals cube root N minus cube of j and it was not already computed, add these values
					abcd.add(a);
					abcd.add(j);
				}
				
				if(abcd.size() == 4) {		// if 2 pairs found >> it is a taxicab number
					taxicabs.add(new Taxicab(i, abcd.get(0), abcd.get(1), abcd.get(2), abcd.get(3)));
					break;
				}
			}
		}
		System.out.println(taxicabs);
	}
	
	// testing
	public static void main(String[] args) {
		Ex24TaxicabNumbers.taxicab(10000);
	}

}
