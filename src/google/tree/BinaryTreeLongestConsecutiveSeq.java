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
public class BinaryTreeLongestConsecutiveSeq {

    public int longestConsecutive_divideConquer(TreeNode root) {
        if (root == null) return 0;
        int[] max = new int[1];
        longestPathStartFrom(root, max);
        return max[0];
    }

    public int longestPathStartFrom(TreeNode cur, int[] max) {
        if (cur == null) return 0;
        int startFromLeftPath = longestPathStartFrom(cur.left, max);
        int startFromRightPath = longestPathStartFrom(cur.right, max);

        int startFromCurMax = 1;
        // check if consective with left and right
        if (cur.left != null && (cur.val + 1 == cur.left.val)) {
            startFromCurMax = Math.max(startFromCurMax, startFromLeftPath + 1);
        }
        if (cur.right != null && (cur.val  + 1 == cur.right.val)) {
            startFromCurMax = Math.max(startFromCurMax, startFromRightPath + 1);
        }
        max[0] = Math.max(max[0], startFromCurMax);
        return startFromCurMax;
    }

    Integer maxLen = 1;
    // Solution 2: with a global variable: maxLen
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;

        // curlen: max len starting from current node
        return recurLongest(root, root, 1);
    }

    public int recurLongest(TreeNode prev, TreeNode cur, int curLen) {
        if (cur == null) return curLen;
        if (cur.val == prev.val + 1) { // consecutive
            curLen ++;
        }
        else {
            curLen = 1;
        }

        return Math.max(curLen, Math.max(recurLongest(cur, cur.left, curLen), recurLongest(cur, cur.right, curLen)));
    }
}
