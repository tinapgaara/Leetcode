package facebook.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 5/28/17.
 *
 * 404. Sum of Left Leaves Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 48788
 Total Submissions: 104419
 Difficulty: Easy
 Contributor: LeetCode
 Find the sum of all left leaves in a given binary tree.

 Example:

 3
 / \
 9  20
 /  \
 15   7

 There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumOfLeftLeaves {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        return leftVal(root.left, 0) + leftVal(root.right, 1);
    }

    public int leftVal(TreeNode root, int dir) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            if (dir == 0) {
                // left side
                return root.val;
            }
        }
        return leftVal(root.left, 0) + leftVal(root.right, 1);
    }
}
