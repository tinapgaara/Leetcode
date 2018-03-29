package facebook.rangesum;

/**
 * Created by yingtan on 3/17/18.
 *
 * 23.1. Augmented BST tree
 Each node stores count of nodes in the BST rooted at this node (including itself)

 */
public class KeysLessThanTarget {
    public class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        int count;
    }
    public int findNumberNodesLessThan(TreeNode root, int key) {
        if (root == null) return 0;
        if (root.val < key) {
            int count = 1;
            if (root.left != null) {
                count = count + root.left.count;
            }
            return count + findNumberNodesLessThan(root.right, key);
        }
        else {
            return findNumberNodesLessThan(root.left, key);
        }
    }
}
