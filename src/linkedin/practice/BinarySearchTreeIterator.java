package linkedin.practice;

import tree.TreeNode;

import java.util.Stack;

/**
 * Created by yingtan on 11/22/17.
 */
public class BinarySearchTreeIterator {

    Stack<TreeNode> stack = new Stack<TreeNode>();

    public BinarySearchTreeIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public boolean hasNext() {
        return ! stack.isEmpty();
    }

    public int next() {
        TreeNode cur = stack.pop();
        int res = cur.val;
        if (cur.right != null) {
            cur = cur.right;
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }
        return res;
    }
}
