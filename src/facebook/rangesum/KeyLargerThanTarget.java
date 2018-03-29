package facebook.rangesum;

/**
 * Created by yingtan on 3/17/18.
 */
public class KeyLargerThanTarget {
    public class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        int count;
    }
    public int findNumberNodesLargerThan(TreeNode root, int key) {
        if (root == null) return 0;
        if (root.val > key) {
            int count = 1;
            if (root.right != null) {
                count = count + root.right.count;
            }
            return count + findNumberNodesLargerThan(root.left, key);
        }
        else {
            return findNumberNodesLargerThan(root.right, key);
        }
    }

}
