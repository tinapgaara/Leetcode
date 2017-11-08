package tree;

/**
 * Created by yingtan on 9/27/15.
 */
public class CountUniValueSubTree {

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        int[] res = new int[1];
        isUni(root, root.val, res);
        return res[0];
    }

    public boolean isUni(TreeNode root, int preval, int[] res) {
        if (root == null) {
            return true;
        }
        // Important !!! must loop root.left and root.right both
        boolean isLeftUni = isUni(root.left, root.val, res);
        boolean isRightUni = isUni(root.right, root.val, res);
        if (isLeftUni && isRightUni) {
            res[0] ++;
        }
        else{
            return false;
        }
        if (root.val != preval) {
            return false;
        }
        return true;
    }
}
