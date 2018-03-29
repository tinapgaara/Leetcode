package tree;
// tree: does not need yp go through root
public class LongestPathFromLeafToLeaf {
    public int longestPath(TreeNode root) {
        int[] max = new int[1];
        longestPathToLeaf(root, max);
        return max[0];
    }
    public int longestPathToLeaf(TreeNode cur, int[] max) {
        if (cur == null) return 0;
        int left = longestPathToLeaf(cur.left, max);
        int right = longestPathToLeaf(cur.right, max);
        int acrossCur = left + right;
        max[0] = Math.max(max[0], acrossCur);
        return Math.max(left, right) + 1; // return longest path from cur to any one of leaves.
    }
}
