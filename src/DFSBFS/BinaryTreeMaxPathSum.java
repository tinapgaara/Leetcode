package DFSBFS;

import tree.TreeNode;

/**
 * Created by yingtan on 10/10/15.
 */
public class BinaryTreeMaxPathSum {
    /*
    * Leetcode: Binary Tree Maximum Path Sum
    *
    *   Given a binary tree, find the maximum path twoPointers.sum.

        For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.

        For example:
        Given the below binary tree,

           1
          / \
         2   3

         Return 6.
    */

    public int maxPathSum(TreeNode root) {

        int[] maxNum = new int[1];
        maxNum[0] = Integer.MIN_VALUE;

        recurMaxPathSum(root, maxNum);
        return maxNum[0];
    }

    public int[] recurMaxPathSum(TreeNode root, int[] maxNum) {
        int[] res = new int[2];
        if (root == null) {
            res[0] = 0; // save the max value in this root's left branch subtree
            res[1] = 0; // save the max value in this root's right branch subtree
            return res;
        }
        int val = root.val;
        int[] left = recurMaxPathSum(root.left, maxNum);
        int[] right = recurMaxPathSum(root.right, maxNum);

        int maxLeft = Math.max(left[0], left[1]);
        int maxRight = Math.max(right[0], right[1]);

        res[0] = Math.max(maxLeft + val, val); // compare the maxLeft value with current root's val
        res[1] = Math.max(maxRight + val, val); // compare the maxRight value with current root's val
        int maxLeftOrRight = Math.max(res[0], res[1]);

        //calculate max value across this root
        maxNum[0] = Math.max(maxNum[0], maxLeft + val + maxRight);
        //calculate max value in a single branch substree
        maxNum[0] = Math.max(maxNum[0], maxLeftOrRight);

        return res;
    }
}
