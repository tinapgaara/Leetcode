package vmware;

import tree.TreeNode;

import java.util.*;

/**
 * Created by yingtan on 4/18/18.
 */
public class KthSmallestElementInABST {

    public int kthSmallest(TreeNode root, int k) {
        int[] ks = new int[1];
        ks[0] = k;
        return kthSmallest_recur(root, ks);

        //return kthSmallest_count(root, k);
        //return kthSmallest_iter(root, k);
    }
    public Integer kthSmallest_recur(TreeNode root, int[] k) {
        if (root == null) return null;
        Integer left = kthSmallest_recur(root.left, k);
        if (left == null) {
            k[0] -- ;
            if (k[0] == 0) {
                return root.val;
            }
            else {
                return kthSmallest_recur(root.right, k);
            }
        }
        else {
            return left;
        }
    }
    public int kthSmallest_iter(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode cur = root;
        while(cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        while(! stack.isEmpty()) {
            cur = stack.pop();
            k --;
            if (k == 0) {
                return cur.val;
            }
            if (cur.right != null) {
                cur = cur.right;
                while(cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
        return -1;
    }
    public int kthSmallest_count(TreeNode root, int k) {
        if (root == null) return 0;
        int left = count(root.left);
        if (left < k) {
            return kthSmallest_count(root.left, k);
        }
        else if (left + 1 == k) {
            return root.val;
        }
        else {
            return kthSmallest_count(root.right, k - left - 1);
        }
    }
    public int count(TreeNode root) {
        if (root == null) return 0;
        return count(root.left) + count(root.right) + 1;
    }
}
