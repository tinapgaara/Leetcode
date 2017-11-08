package google.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 10/28/17.
 */
public class LongestUnivalueSubTree {

    public int longestUnivaluePath(TreeNode root) {
        int[] max = new int[1];
        if (root == null) return 0;
        recurPath(root, root.val, max);
        return max[0];
    }
    // longest univ path starting from the current node
    public int recurPath(TreeNode root, int preval, int[] max) {
        if (root == null) return 0;
        int left = recurPath(root.left, root.val, max);
        int right = recurPath(root.right, root.val, max);

        max[0] = Math.max(max[0], left + right);
        // important !!! key
        if (root.val != preval) {
            return 0;
        }
        return Math.max(left, right) + 1;
    }
}
