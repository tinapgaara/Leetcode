package tree;

/**
 * Created by yingtan on 12/10/17.
 */
public class AugmentedBST {

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

    public int findNumberNodesLargerThan(TreeNode root, int key) {
        if (root == null) return 0;
        if (root.val > key) {
            int count = 1;
            if (root.right != null) {
                count = count + root.right.count;
            }
            return count + findNumberNodesLessThan(root.left, key);

        }
        else {
            return findNumberNodesLessThan(root.right, key);
        }
    }

    public int findNumberNodesBetween(TreeNode root, int lowKey, int highKey) {
        int smallerNodes = findNumberNodesLessThan(root, lowKey);
        int largerNodes = findNumberNodesLargerThan(root,highKey);
        return root.count - smallerNodes - largerNodes;
    }
}
