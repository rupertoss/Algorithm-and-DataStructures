
package exercises;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Percolation {
	
	private boolean [] open;
	
    private int[] parent;  // parent[i] = parent of i
    private int[] size;    // size[i] = number of sites in tree rooted at i
    private int count;     // number of components
		
	private Percolation(int size) {
		open = new boolean[(size*size)+2];
        parent = new int[(size*size)+2];
        this.size = new int[(size*size)+2];

        for (int i = 0; i < open.length; i++) {
        	open[i] = false;
            parent[i] = i;
            this.size[i] = 1;
        }
		
		open[0] = true;
		open[open.length - 1] = true;
		
		count = 0;
		
		do {
			int random = ThreadLocalRandom.current().nextInt(1, (size*size)+1);
			open(random, size);
		} while (!connected(open.length - 1));
		
		
	}
	
	private void open(int site, int size) {
		
		if (open[site]) 
			return;
		
		open[site] = true;
		
		if(site%size != 1) 
			union(site, site - 1);
		
		if(site%size != 0)
			union(site, site + 1);
		
		if(!(site <= size)) {
			union(site, site - size);
		} else {
			union(site, 0);
		}
		
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
	        count++;
    	}
    }
    
    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param  site the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
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
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the size of the block");
		int size = scan.nextInt();
		System.out.println("Enter the number of runs");
		int runs = scan.nextInt();
		scan.close();
		
 
		List<Percolation> percolations = new LinkedList<Percolation>();
		for (int i=0; i < runs; i++) {
			percolations.add(new Percolation(size));
		}
		
		double sum = 0;
		double mean;
		double tmpStdDev = 0;
		
		for (Percolation per: percolations) {
			sum += per.count;
		}
		
		mean = sum/runs;
		
		for (Percolation per: percolations) {
			tmpStdDev += Math.pow((double)per.count - mean, 2);
		}
		
		double standardDeviation = Math.sqrt(tmpStdDev/runs);
		double bottomBorder = mean - (1.96*Math.sqrt(standardDeviation))/Math.sqrt(runs);
		double topBorder = mean + (1.96*Math.sqrt(standardDeviation))/Math.sqrt(runs);
		
		System.out.printf("\nPerlocation stats >> block size: %d , number of runs: %d", size, runs);
		System.out.println("\nTRESHOLD = " + (mean)/(size*size));
		System.out.println("mean = " + mean);
		System.out.println("stddev = " + standardDeviation);
		System.out.println("95% confidence interval = [" + bottomBorder + ", " + topBorder + "]");
		
	}
}
