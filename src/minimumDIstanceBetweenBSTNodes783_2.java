/**
 * 
 * 783. Minimum Distance Between BST Nodes
 * 
 *  Approach 2: In-Order Traversal
 * 
 *  Intuition and Algorithm:
 *  - In a binary search tree, an in-order traversal outputs the values of the tree in order.
 *  - By remembering the previous value in this order, we could iterate over each possible difference, keeping the smallest one.
 *
 */
public class minimumDIstanceBetweenBSTNodes783_2 {
	
	Integer prev, ans;
	
	public int minDiffInBST(TreeNode root) {
		prev = null; // set the pointer to null
		ans = Integer.MAX_VALUE;
		dfs(root);
		return ans;
	}
	
	public void dfs(TreeNode node) {
		if (node == null) return;
		
		dfs(node.left);
		
		if (prev != null) 
			ans = Math.min(ans, node.val - prev);
		prev = node.val; // remember the previous value of node 
		dfs(node.right);
		
	}

}

/**
 * Complexity Analysis:
 * 
 *  -Time Complexity:
 *   - O(N)
 *   - where N is the number of nodes in the tree.
 *   - We iterate over every node once.
 *  
 *  - Space Complexity:
 *   - O(H)
 *   - where H is the height of the tree
 *   - This is the space used by the implicit call stack when calling dfs.
 * 
 */




