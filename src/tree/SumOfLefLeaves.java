package tree;

/**
 * Created by yingtan on 3/17/18.
 */
import java.util.*;
public class SumOfLefLeaves {

    // time : o(n)  space: o(h) recursion
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) {
                res = res + root.left.val;
            }
            else {
                res = res + sumOfLeftLeaves(root.left);
            }
        }
        if (root.right != null) {
            res = res + sumOfLeftLeaves(root.right);
        }
        return res;
    }
    // using stack, time: o(n) , space: o(n)
    // time : o(n)  space: o(h)
    public int sumOfLeftLeaves_stack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int res = 0;
        while(! stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left != null) {
                if (cur.left.left == null && cur.left.right == null) { // left leave
                    res = res + cur.left.val;
                }
                else {
                    stack.push(cur.left);
                }
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        return res;
    }

}
