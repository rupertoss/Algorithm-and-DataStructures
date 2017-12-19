
package exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Percolation {
	
	private boolean [][] open; // open [i][j] = is site [i][j] open
	private boolean top;
	private boolean bottom;
	
    private int[] parent;  // parent[i] = parent of i
    private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
    private int componentCount;     // number of components
	private int openCount; // number of open sites
	
	private static int n; // size of the block
	private static int T; // number of test runs
	
	// create n-by-n grid, with all sites blocked
	public Percolation(int n, int T) {
		if (n < 0) throw new IllegalArgumentException();
		if (T < 0) throw new IllegalArgumentException();
		
		open = new boolean [n+1][n+1];
		for (int i=1; i<n+1; i++) {
			for (int j=1; j<n+1; j++) {
				open[i][j] = false;
			}
		}
		
		parent = new int [((n*n) + 2)];
		rank = new byte [((n*n) + 2)];
		for (int i=0; i < ((n*n)+2); i++) {
			parent[i] = i;
			rank[i] = 0;
		}
		
		openCount = 0;
		componentCount = n*n;
		top = true;
		bottom = true;
		
		do {
			Random rand = new Random();
			int row;
			int col;
			do {
			row = rand.nextInt(n)+1;
			col = rand.nextInt(n)+1;
			} while (isOpen(row,col));
//			System.out.println(row + "||||" + col);
			open(row,col);
//			System.out.println("=========parents of sites==================");
//			int root = find(0);
//			System.out.println("the root" + root);
//			for (int i=0; i < ((n*n)+2); i++) {
//				System.out.println(parent[i]);
//			}
//			
//			System.out.println("===========================");
//			System.out.println("============same as top===============");
//			for (int i=0; i < ((n*n)+2); i++) {
//				if (find(i) == find(root))
//					System.out.println(i);
//			}
//			
//			System.out.println("===========================");
		} while (!percolates());
		
//		System.out.println("===========================");
		
//		int root = find(0);
//		for (int i=0; i < ((n*n)+2); i++) {
//			if (parent[i] == root);
//			System.out.println(i);
//		}
//		
//		System.out.println("===========================");
		
//		while (!percolates()) {
//			Random rand = new Random();
//			int row;
//			int col;
//			do {
//			row = rand.nextInt(n)+1;
//			System.out.println();
//			col = rand.nextInt(n)+1;
//			} while (isOpen(row,col));
//			System.out.println(row + "||||" + col);
//			open(row,col);
//		}
	}
	
	
	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		if (!isOpen(row, col)) {
			open[row][col] = true; 
			if (row != 1) {
				if(isOpen(row - 1, col))
					union(row, col, row - 1, col);
			}
			if (row != n) {
				if (isOpen(row + 1, col))
					union(row, col, row + 1, col);
			}
			if (col != 1) {
				if(isOpen(row, col - 1))
						union(row, col, row, col - 1);
			}
			if(col != n) {
				if(isOpen(row ,col + 1)) 
					union(row, col, row, col + 1);
			}
			
//			if (!top && row == 1) {
//				top = true;
//				union(0, 5, row, col);
//			}
//				
//			if (!bottom && row == n) {
//				bottom = true;
//				union(n, n+1, row, col);
//			}
			
			if (row == 1) {
				union(0, n, row, col);
			}
				
			if (row == n) {
				union(n, n+1, row, col);
			}
				
			openCount++;
		}
	}
	
	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		return open[row][col];
	}
	
	// is site (row, col) full?
	public boolean isFull(int row, int col) {
		int site = (row-1)*n + col;
		return connected(0, site);
	}
	
	// number of open sites
	public int numberOfOpenSites() {
		return openCount;
	}
	
	// does the system percolate?
	public boolean percolates() {
//		boolean result = connected(0, (n*n)+1);
//		System.out.println(result);
//		return result;
		return connected(0, (n*n)+1);
	}
	
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    
    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
        }
        return p;
    }
    
    // validate that p is a valid index
    private void validate(int site) {
        int num = parent.length;
//        System.out.println("num" + num + " and the site is " + site);
        if (site < 0 || site > (num+1)) {
            throw new IllegalArgumentException("index " + site + " is not between 0 and " + (num+1));  
        }
    }
    
    public void union(int row1, int col1, int row2, int col2) {
    	int site1 = (row1-1)*n + col1;
    	int site2 = (row2-1)*n + col2;
        int rootSite1 = find(site1);
        int rootSite2 = find(site2);
        if (rootSite1 == rootSite2) return;

        // make root of smaller rank point to root of larger rank
        if      (rank[rootSite1] < rank[rootSite2]) parent[rootSite1] = rootSite2;
        else if (rank[rootSite1] > rank[rootSite2]) parent[rootSite2] = rootSite1;
        else {
            parent[rootSite2] = rootSite1;
            rank[rootSite1]++;
        }
        componentCount--;
    }

	  // test client (optional)
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insert size of a block");
		n = scanner.nextInt();
		System.out.println("Insert the number of test runs");
		T = scanner.nextInt();
		scanner.close();
//		System.out.println("test1");
		
		List<Percolation> tests = new ArrayList<>();
		for (int i=0; i < T; i++) {
			tests.add(new Percolation(n,T));
//			System.out.println("test no." + i);
		}
		
		
//		System.out.println("test2");
		
		
		double sum = 0;
		for (Percolation per : tests) {
			sum += ((per.openCount)/(double)(n*n));
//			System.out.println("sum " + sum);
		}
		double mean = sum/T;
		
		double tmpStdDev = 0;
		for (Percolation per : tests) {
			tmpStdDev += Math.pow((double)per.openCount - mean, 2);
		}
		double standardDeviation = Math.sqrt(tmpStdDev/(T));
		double bottomBorder = mean - (1.96*Math.sqrt(standardDeviation))/Math.sqrt(T);
		double topBorder = mean + (1.96*Math.sqrt(standardDeviation))/Math.sqrt(T);
		
		
		System.out.printf("Perlocation stats %d %d", n, T);
		System.out.println("\nmean = " + mean);
		System.out.println("stddev = " + standardDeviation);
		System.out.println("95% confidence interval = [" + bottomBorder + ", " + topBorder + "]");
		
		
		
		
	}

}




