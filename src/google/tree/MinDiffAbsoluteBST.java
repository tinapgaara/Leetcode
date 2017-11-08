package google.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 2/27/17.
 *
 * 530. Minimum Absolute Difference in BST
 *
 * Inorder traverse using Prev pointer and cal each two node's diff
 *
 * Min diff must between two adj nodes in inorder traverse
 *
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

 Example:

 Input:

 1
 \
 3
 /
 2

 Output:
 1

 Explanation:
 The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 *
 */
public class MinDiffAbsoluteBST {

    Integer min = Integer.MAX_VALUE;
    TreeNode prev = null;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;

        // inorder traverse
        getMinimumDifference(root.left);

        if (prev != null) {
            min = Math.min(min, root.val - prev.val);
        }

        prev = root;

        getMinimumDifference(root.right);

        return min.intValue();
    }
}
