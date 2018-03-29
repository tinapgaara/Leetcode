package tree;

/**
 * Created by yingtan on 3/17/18.
 */
import java.util.*;
public class FullBinaryTree {
    public boolean isFull(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(! queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
            if ( (cur.left != null && cur.right == null) || (cur.right != null && cur.left == null) ) {
                return false;
            }
        }
        return true;
    }
    // time : o(n) , space: o(h)
    public boolean isFullRecur(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) {
            return true;
        }
        else if (root.left != null &&  root.right != null) {
            return isFullRecur(root.left) && isFullRecur(root.right);
        }
        else {
            return false;
        }
    }

}
