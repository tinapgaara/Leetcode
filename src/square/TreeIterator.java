package square;

import tree.TreeNode;

import java.util.Stack;

/**
 * Created by yingtan on 10/18/17.
 *
 *  写一个TREE, 然后写TREE ITERATOR，然后写一个特殊的TREE， 类似红黑树要实现BALANCE.
 *
 *  173. Binary Search Tree Iterator
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

 Calling next() will return the next smallest number in the BST.

 Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */
public class TreeIterator {
    // using stack
    Stack<TreeNode> stack = new Stack<TreeNode>();
    // keep push left node
    public TreeIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    public boolean hasNext() {
        if (stack.isEmpty()) return false;
        else return true;
    }
    public int next() {
        TreeNode node = stack.pop();
        int res = node.val;
        if (node.right != null) {
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return res;
    }
}
