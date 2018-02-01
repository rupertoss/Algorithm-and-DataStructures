package exercises;


public class Ex26CheckIfABinaryTreeIsABinarySearchTree {
	
	private Node root;				// root of the BT
	
    private class Node {
        private int key;           // sorted by key
        private Node left, right;  // left and right subtrees
    }
    
    public Node getRoot() {
		return root;
	}

	public static boolean isBST(Ex26CheckIfABinaryTreeIsABinarySearchTree bt) {
    	return isBST(bt.getRoot());
    }
    
    private static boolean isBST(Node x) {
    		
    	if(x.left != null)					// compare with left child if exists 
    		if(x.key < x.left.key) {
				return false;				// return false if violating the rule of BST
    	}
    		
    	if(x.right != null)					// compare with right child if exists
    		if(x.key > x.right.key) {
				return false;				// return false if violating the rule of BST
    	}
    		
    	if(x.left != null)					// go deeper into left subtree
			isBST(x.left);
    	
    	if(x.right != null) 				// go deeper into right subtree
    		isBST(x.right);
    	
    	return true;
    }
    
}
