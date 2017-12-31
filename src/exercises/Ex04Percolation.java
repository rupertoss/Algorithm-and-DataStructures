/*
 * The solution is based on WeightedQuickUnionPathCompression algorithm.
 * 
 * ==========================================
 * Exemplary array (size = 5) of sites looks like:
 * 			0				<- top site
 * 1	2	3	4	5
 * 6	7	8	9	10
 * 11	12	13	14	15	
 * 16	17	18	19	20
 * 21	22	23	24	25
 * 			26				<- bottom site
 * 
 * ==============================================
 * Exemplary results 
 * Enter the size of the block
 * 1000
 * Enter the number of runs
 * 100
 * 
 * Perlocation stats >> block size: 1000 , number of runs: 100
 * 
 * Threshold = 0.59258189
 * Stddev = 0.0030438897611280197
 * 95% confidence interval = [0.5817682842616954, 0.6033954957383045]
 */

package exercises;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Ex04Percolation {
	
	private boolean [] open;
	
    private int[] parent;  // parent[i] = parent of i
    private int[] size;    // size[i] = number of sites in tree rooted at i
    private int count;     // number of sites opened
	
    /**
     * Runs the percolation test on block with the size {@code size}
     * 
     * @param size the integer representing size of the block
     */
	private Ex04Percolation(int size) {
		
		//initializing arrays
		open = new boolean[(size*size)+2];
        parent = new int[(size*size)+2];
        this.size = new int[(size*size)+2];

        //setting default values
        for (int i = 0; i < open.length; i++) {
        	open[i] = false;
            parent[i] = i;
            this.size[i] = 1;
        }
		count = 0;
		
        //top and bottom site is always open
		open[0] = true;
		open[open.length - 1] = true;
		
		//processing opening sites until top and bottom is connected
		do {
			int random = ThreadLocalRandom.current().nextInt(1, (size*size)+1);
			open(random, size);
		} while (!connected(open.length - 1));
		
		
	}
	
	/**
     * Opens the site {@code site} (if not open already) 
     * and connects to the adjacent open sites
     * 
	 * @param site the integer representing site
	 * @param size the integer representing size of the block
	 */
	private void open(int site, int size) {
		
		//check if already open
		if (open[site]) 
			return;
		
		//opening
		open[site] = true;
		
		//counting the number of open sites
        count++;
		
		//connect to the west
		if(site%size != 1) 
			union(site, site - 1);
		
		//connect to the east
		if(site%size != 0)
			union(site, site + 1);
		
		//connect to the north, if first row connect to the top
		if(!(site <= size)) {
			union(site, site - size);
		} else {
			union(site, 0);
		}
		
		//connect to the south, if last row connect to the bottom
		if(!(site > (size - 1)*size)) {
			union(site, site + size);
		} else {
			union(site, (size*size) + 1);
		}
	}
	
    /**
     * Merges the component containing site {@code site1} with the 
     * the component containing site {@code site2}.
     *
     * @param  site1 the integer representing one site
     * @param  site2 the integer representing the other site
     */
    public void union(int site1, int site2) {
    	if (open[site2]) {
    		
	        int rootSite1 = find(site1);
	        int rootSite2 = find(site2);
	        if (rootSite1 == rootSite2) return;
	
	        // make smaller root point to larger one
	        if (size[rootSite1] < size[rootSite2]) {
	            parent[rootSite1] = rootSite2;
	            size[rootSite2] += size[rootSite1];
	        }
	        else {
	            parent[rootSite2] = rootSite1;
	            size[rootSite1] += size[rootSite2];
	        }
    	}
    }
    
    /**
     * Returns the component identifier for the component containing site {@code site}.
     *
     * @param  site the integer representing one site
     * @return the component identifier for the component containing site {@code site}
     */
    public int find(int site) {
        int root = site;
        while (root != parent[root])
            root = parent[root];
        while (site != root) {
            int newSite = parent[site];
            parent[site] = root;
            site = newSite;
        }
        return root;
    }
    
    /**
     * Returns true if the the top and bottom site are connected.
     *
     * @param  bottom the integer representing bottom site
     * @return {@code true} if the two sites top and bottom are connected;
     *         {@code false} otherwise
     */
    public boolean connected(int bottom) {
        return find(0) == find(bottom);
    }


	public static void main (String[] args) {
		
		//getting user input 
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the size of the block");
		int size = scan.nextInt();
		System.out.println("Enter the number of runs");
		int runs = scan.nextInt();
		scan.close();
		
		//running the percolations
		List<Ex04Percolation> percolations = new LinkedList<Ex04Percolation>();
		for (int i=0; i < runs; i++) {
			percolations.add(new Ex04Percolation(size));
		}
		
		//statistics data
		double sum = 0;
		double thresholdMean;
		double tmpStdDev = 0;
		
		for (Ex04Percolation per: percolations) {
			sum += per.count/(double)(size*size);
		}
		
		thresholdMean = sum/runs;
		
		for (Ex04Percolation per: percolations) {
			tmpStdDev += Math.pow((double)per.count/(double)(size*size) - thresholdMean, 2);
		}
		
		double standardDeviation = Math.sqrt(tmpStdDev/runs);
		double bottomBorder = thresholdMean - (1.96*Math.sqrt(standardDeviation))/Math.sqrt(runs);
		double topBorder = thresholdMean + (1.96*Math.sqrt(standardDeviation))/Math.sqrt(runs);
		
		//printing results
		System.out.printf("\nPerlocation stats >> block size: %d , number of runs: %d", size, runs);
		System.out.println("\n\nThreshold = " + thresholdMean);
		System.out.println("Stddev = " + standardDeviation);
		System.out.println("95% confidence interval = [" + bottomBorder + ", " + topBorder + "]");
	}
}
