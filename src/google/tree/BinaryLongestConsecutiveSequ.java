package google.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 11/1/15.
 */
/*
*
*Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree
along the parent-child connections. The longest consecutive path need to be from parent
to child (cannot be the reverse).
*
*   1
    \
     3
    / \
   2   4
        \
         5
*
* Longest consecutive sequence path is 3-4-5, so return 3.
*
*  2
    \
     3
    /
   2
  /
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
*
* */
public class BinaryLongestConsecutiveSequ {

    public int longestConsecutive(TreeNode root) {
        // Pay attention !! must do left side and right side in main function
        if (root == null) return 0;
        int leftMax = recurLongestConsequence(root, root.left, 1);
        int rightMax = recurLongestConsequence(root, root.right, 1);
        return Math.max(leftMax, rightMax);
    }

    public int recurLongestConsequence(TreeNode parent, TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        if (root.val == parent.val + 1) {
            int leftMax  = recurLongestConsequence(root, root.left, depth+1);
            int rightMax  = recurLongestConsequence(root, root.right, depth+1);

            return Math.max(leftMax, rightMax);
        }
        else {
            int leftMax  = recurLongestConsequence(root, root.left, 1);
            int rightMax  = recurLongestConsequence(root, root.right, 1);

            int max = Math.max(rightMax, leftMax);
            return Math.max(max, depth);
        }
    }
}
