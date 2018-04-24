package vmware;

/**
 * Created by yingtan on 4/18/18.
 *
 * 145. Binary Tree Postorder Traversal
 DescriptionHintsSubmissionsDiscussSolution
 Given a binary tree, return the postorder traversal of its nodes' values.

 For example:
 Given binary tree [1,null,2,3],

 1
 \
 2
 /
 3


 return [3,2,1].
 */
import tree.TreeNode;

import java.util.*;
public class BinaryTreePostOrderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        return postOrder_iter(root);
        //postOrder_recur(root, res);
        //return res;
    }
    public List<Integer> postOrder_iter(TreeNode root) {
        LinkedList<Integer> linkedres = new LinkedList<>();
        if (root == null) return linkedres;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(! stack.isEmpty()) {
            TreeNode cur = stack.pop();
            linkedres.addFirst(cur.val);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        return linkedres;
    }
    public void postOrder_recur(TreeNode cur, List<Integer> res) {
        if (cur == null) return;
        postOrder_recur(cur.left, res);
        postOrder_recur(cur.right, res);
        res.add(cur.val);
    }
}
