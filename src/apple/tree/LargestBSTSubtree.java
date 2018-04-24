package apple.tree;

import tree.TreeNode;

public class LargestBSTSubtree {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        int[] max = new int[1];
        recurFind(root, max);
        return max[0];
    }
    // int[] : min, max, count
    public int[] recurFind(TreeNode cur, int[] max) {
        if (cur == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        int[] left = recurFind(cur.left, max);
        int leftmin = left[0];
        int leftmax = left[1];
        int[] right = recurFind(cur.right, max);
        int rightmin = right[0];
        int rightmax = right[1];
        if (cur.val > leftmax && cur.val < rightmin) {
            // valid bst from cur
            int count = left[2] + right[2] + 1;
            max[0] = Math.max(max[0], count);
            // to deal with null left tree and null right tree
            // (MAX_VALUE, MIN_VALUE) - (5), then, the maxvalue change to 5 instead of MAX_VALUE
            if (leftmin == Integer.MAX_VALUE) {
                leftmin = cur.val;
            }
            if (rightmax == Integer.MIN_VALUE) {
                rightmax = cur.val;
            }
            return new int[]{leftmin, rightmax, count};
        }
        else {
            // not valid
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
        }
    }
}
