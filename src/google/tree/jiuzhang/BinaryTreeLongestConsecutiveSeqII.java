package google.tree.jiuzhang;

import tree.TreeNode;

/**
 * Created by yingtan on 10/29/17.
 *
 * 549. Binary Tree Longest Consecutive Sequence II
 *
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

 Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

 Example 1:
 Input:
 1
 / \
 2   3
 Output: 2
 Explanation: The longest consecutive path is [1, 2] or [2, 1].
 Example 2:
 Input:
 2
 / \
 1   3
 Output: 3
 Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 */
public class BinaryTreeLongestConsecutiveSeqII {

    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        int[] max = new int[1];
        longestPathStartFrom(root, max);
        return max[0];
    }

    public int[] longestPathStartFrom(TreeNode cur, int[] max) {
        if (cur == null) return new int[]{0,0};
        int startFromLeftPath[] = longestPathStartFrom(cur.left, max);
        int startFromRightPath[] = longestPathStartFrom(cur.right, max);

        int startFromCurMaxIncrease = 1;
        int startFromCurMaxDecrease = 1;
        // check if consective with left and right
        if (cur.left != null && (cur.val + 1 == cur.left.val)) {
            startFromCurMaxIncrease = Math.max(startFromCurMaxIncrease, startFromLeftPath[0] + 1);
        }
        if (cur.right != null && (cur.val  + 1 == cur.right.val)) {
            startFromCurMaxIncrease = Math.max(startFromCurMaxIncrease, startFromRightPath[0] + 1);
        }
        if (cur.left != null && (cur.val - 1 == cur.left.val)) {
            startFromCurMaxDecrease = Math.max(startFromCurMaxDecrease, startFromLeftPath[1] + 1);
        }
        if (cur.right != null && (cur.val - 1 == cur.right.val)) {
            startFromCurMaxDecrease = Math.max(startFromCurMaxDecrease, startFromRightPath[1] + 1);
        }
        max[0] = Math.max(max[0], startFromCurMaxIncrease + startFromCurMaxDecrease - 1);

        return new int[]{startFromCurMaxIncrease, startFromCurMaxDecrease};
    }
}
