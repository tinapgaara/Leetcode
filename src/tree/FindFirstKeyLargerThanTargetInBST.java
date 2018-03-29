package tree;

/**
 * Created by yingtan on 12/9/17.
 */
public class FindFirstKeyLargerThanTargetInBST {
    // time: o(h)
    public int firstLarger(TreeNode root, int target) {
        TreeNode cur = root;
        int first = -1;
        while(cur != null) {
            if (cur.val > target) {
                first = cur.val;
                cur = cur.left;
            }
            else {
                cur = cur.right;
            }
        }
        return first;
    }
}
