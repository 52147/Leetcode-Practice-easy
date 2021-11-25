import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 783. Minimum Distance Between BST Nodes
 * 
 * Q.
 *  Given the root a Binary Search Tree (BST), return the minimum difference between the values of any 2 different nodes in the tree.
 *  
 *  
 * Example 1:
 * root
 *  4 -> 2 ->1 
 *         ->3
 *    -> 6
 *    
 *  Input: root = [4, 2, 6, 1, 3]
 *  Output: 1
 *  
 *  Example 2:
 *  
 *  1 -> 0
 *    -> 48 -> 12
 *          -> 49
 *          
 *  Input: root = [1, 0, 48, null, null ,12 , 49]
 *  Output: 1
 *  
 * Constraints:
 *  - The number of nodes in the tree is in the range [2, 100]
 *  - 0 <= Node.val <= 10^5
 *  
 *  Solution:
 *  
 *   Approach 1: Write to Array
 *   
 *   Intuition and Algorithm
 *    - Write all the values to an array, then sort it.
 *    - The minimum distance must occur between 2 adjacent value in the sorted list.
 *            
 * 
 *
 */
/**
 * Definition for a binary tree node.
 * 
 * public class TreeNode{
 * 		int val;
 * 		TreeNode left;
 * 		TreeNode right;
 * 		TreeNode(){}
 * 		
 * 		TreeNode(int val){this.val  = val;}
 * 		
 * 		TreeNode(int val, TreeNode left, TreeNode right){
 * 			this.val = val;
 * 			this.left = left;
 * 			this.right = right;
 * 		}
 * }
 * 
 *
 */
public class minimumDistanceBetweenBSTNodes783_1 {
	
	
	List <Integer> vals;
	
	public int minDiffInBST(TreeNode root) {
		// Create a ArrayList to store all the values
		vals = new ArrayList();
		
		// Add the node in tree
		dfs(root);
		
		// Sort the values
		Collections.sort(vals);
		
		int ans = Integer.MAX_VALUE;
		
		for(int i = 0; i< vals.size()-1; ++i) {
			// Find the minimum distance between 2 adjacent nodes
			ans = Math.min(ans, vals.get(i+1)-vals.get(i));
		}
		return ans;	
		}
		
		public void dfs(TreeNode node) {
			// If there is no node, return nothing
			if (node == null ) return;
			// add the node value in the vals list
			vals.add(node.val);
			// call the dfs with left node
			dfs(node.left);
			// call the dfs with right node
			dfs(node.right);
		}
		
	}

/**
 * Complexity Analysis:
 * 
 *  - Time Complexity:
 *    - O(N log N)
 *    - where N is the number of nodes in the tree.
 *    - The complexity comes form the sorting operation.
 *    
 *  - Space Complexity:S
 *    - O(N)
 *    - the size of vals  
 * 
 */




