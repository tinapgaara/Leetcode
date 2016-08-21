package tree;

/**
 * Created by yingtan on 9/22/15.
 */
public class ClostValueBST {

    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return 0;
        }
        double minDiff = Double.MAX_VALUE;
        return recurCloseValue(root, target, minDiff, -1);
    }
    public int recurCloseValue(TreeNode root, double target, double minDiff, int minNodeVal) {

        if (root == null) {
            return minNodeVal;
        }

        int val = root.val;
        double diff = Math.abs(target - val);
        if (diff < minDiff) {
            minDiff = diff;
            minNodeVal = val;
        }

        if (target > val) {
            return recurCloseValue(root.right, target, minDiff, minNodeVal);
        }
        else if (target < val) {
            return recurCloseValue(root.left, target, minDiff, minNodeVal);
        }
        else {
            return val;
        }
    }
}
