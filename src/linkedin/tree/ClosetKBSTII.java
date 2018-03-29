package linkedin.tree;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yingtan on 11/28/17.
 *
 * 272. Closest Binary Search Tree Value II
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

 Note:
 Given target value is a floating point.
 You may assume k is always valid, that is: k ≤ total nodes.
 You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 */
public class ClosetKBSTII {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<>();
        inorderRecur(root, target, k, res);
        return res;
    }

    public void inorderRecur(TreeNode root, double target, int k, LinkedList<Integer> res) {
        if (root == null) return;
        inorderRecur(root.left, target, k, res);
        if(res.size() == k) {
            if (Math.abs(root.val - target) < Math.abs(res.getFirst() - target)) {
                res.removeFirst();
                res.addLast(root.val);
            }
        }
        else {
            res.addLast(root.val);
        }
        inorderRecur(root.right, target, k, res);
    }
}

