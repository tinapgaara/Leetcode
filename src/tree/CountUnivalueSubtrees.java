package tree;

/**
 * Created by yingtan on 3/6/18.
 *
 * 250. Count Univalue Subtrees
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a binary tree, count the number of uni-value subtrees.

 A Uni-value subtree means all nodes of the subtree have the same value.

 For example:
 Given binary tree,
 5
 / \
 1   5
 / \   \
 5   5   5
 return 4.


 */
public class CountUnivalueSubtrees {
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        int[] count = new int[1];
        recurCount(root, count);
        return count[0];
    }
    public boolean recurCount(TreeNode root, int[] count) {
        if (root == null) return true;
        if (root.left == null && root.right == null) { // leaf
            count[0] = count[0] + 1;
            return true;
        }
        boolean leftIsSame = recurCount(root.left, count);
        boolean rightIsSame = recurCount(root.right, count);
        if (root.left != null && root.right != null) {
            if (leftIsSame && rightIsSame && root.val == root.left.val && root.val == root.right.val) {
                count[0] = count[0] + 1;
                return true;
            }
        }
        else if (root.left != null) {
            if (leftIsSame && root.val == root.left.val) {
                count[0] = count[0] + 1;
                return true;
            }
        }
        else {
            if (rightIsSame && root.val == root.right.val) {
                count[0] = count[0] + 1;
                return true;
            }
        }
        return false;
    }
}
