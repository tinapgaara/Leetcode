package google.tree.jiuzhang;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by yingtan on 10/29/17.
 *
 * 144. Binary Tree Preorder BinaryTreeVerticalOrderTraversal
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary tree, return the preorder traversal of its nodes' values.

 For example:
 Given binary tree {1,#,2,3},
 1
 \
 2
 /
 3
 return [1,2,3].

 Recursive solution is trivial, could you do it iteratively?
 */
public class PreorderTraversal {

    // Sol 1: iterative order
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if (root == null) return res;
        stack.push(root);
        while(! stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return res;
    }

    // Sol 2: recursion traverse: use a global List<Integer> res
    // must use a global variable !!!!  no need to return
    // top - down
    public List<Integer> preorderTraversal_recursion(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        recursion(root, res);
        return res;
    }
    public void recursion(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        recursion(root.left, res);
        recursion(root.right, res);
    }

    // Sol 3: use divide-conquer: return a result : down-top
    public List<Integer> preorderTraversal_divideConquer(TreeNode root) {
        return divideConquer(root);
    }

    public List<Integer> divideConquer(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        List<Integer> left = divideConquer(root.left);
        List<Integer> right = divideConquer(root.right);

        res.add(root.val);
        res.addAll(left);
        res.addAll(right);
        return res;
    }


}
