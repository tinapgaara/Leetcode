package lyft.tree;
import tree.TreeNode;

import java.util.*;
public class SecondLargestBinarySearchTree {
    //  Solution 1:  stack
    public TreeNode secondLargest(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while(root != null) {
            stack.push(root);
            root = root.right;
        }
        int count = 0;
        while(! stack.isEmpty()) {
            TreeNode cur = stack.pop();
            count ++;
            if (count == 2) {
                return cur;
            }
            if (cur.left != null) {
                cur = cur.left;
                while(cur != null) {
                    stack.push(cur);
                    cur = cur.right;
                }
            }
        }
        return null;
    }
    // Solution 2:  recursion
    public TreeNode kLargest_recur(TreeNode root, int k) {
        if (root == null) return null;
        int rightCount = count(root.right);
        if (k <= rightCount) {
            return kLargest_recur(root.right, k);
        }
        else {
            if (k == rightCount + 1) {
                return root;
            }
            else {
                return kLargest_recur(root.left, k - rightCount - 1);
            }
        }
    }
    public int count(TreeNode cur) {
        if (cur == null) return 0;
        return count(cur.left) + count(cur.right) + 1;
    }

}
