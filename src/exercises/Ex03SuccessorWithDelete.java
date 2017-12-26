/* Successor with delete. 
 * 
 * Given a set of N integers S={0,1,...,N?1} and a sequence of requests of the following form:
 *  - Remove x from S
 *  - Find the successor of x: the smallest y in S such that y>=x.
 * design a data type so that all operations (except construction) should take logarithmic time or better.
 */

/**
 * Solution is based on UnionFind with path compression algorithm.
 * Solution maintains the highest site in component to be a root of that component.
 * Deleting site are pointing to the root of the next site. 
 */

package exercises;

public class Ex03SuccessorWithDelete {
	
    private int[] parent;  // parent[i] = parent of i
    
    /**
     * Initializes the basic data structure of {@code n} sites.
     * 
     * @param n the number of sites
     */
    public Ex03SuccessorWithDelete(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    /**
     * Returns a parent of {@code p}. 
     * While finding it compresses the path if necessary.
     * 
     * @param p the number of site
     * @return parent of {@code p}
     */
    public int find(int p) {
        int root = p;
        while (root != parent[root])
            root = parent[root];
        while (p != root) {
            int newp = parent[p];
            parent[p] = root;
            p = newp;
        }
        return root;
    }
    
    /**
     * Returns a successor of deleting site {@code p}
     * 
     * @param p the number of site to be deleted
     * @return the successor of {@code p}
     */
    public int deleteXreturnSuccessor(int p) {
        int rootP = find(p);
        int rootOfNext = find(p+1);
        
        parent[rootP] = rootOfNext;
        return rootOfNext;
    }
}
