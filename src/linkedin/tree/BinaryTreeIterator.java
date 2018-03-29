package linkedin.tree;

import tree.TreeNode;

import java.util.Stack;

/**
 * Created by yingtan on 11/28/17.
 */
public class BinaryTreeIterator {

    // using stack
    Stack<TreeNode> stack = new Stack<TreeNode>();

    // keep push left node
    public BinaryTreeIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (stack.isEmpty()) return false;
        else return true;
    }

    /** @return the next smallest number */
    public int next() {
        // firstly, pop up the node from stack, this is the smallest one
        TreeNode node = stack.pop();
        int res = node.val;
        // then, if this node has right children
        if (node.right != null) {
            node = node.right;
            // keep push left nodes of this right children
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return res;
    }
}
