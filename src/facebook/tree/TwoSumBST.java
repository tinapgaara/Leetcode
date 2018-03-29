package facebook.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 12/5/17.
 *
 * 653. Two Sum IV - Input is a BST
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

 Example 1:
 Input:
 5
 / \
 3   6
 / \   \
 2   4   7

 Target = 9

 Output: True
 Example 2:
 Input:
 5
 / \
 3   6
 / \   \
 2   4   7

 Target = 28

 Output: False
 */
public class TwoSumBST {

    // Sol 1 : using bst property and do dfs
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        return recur(root, root, k);
    }

    public boolean recur(TreeNode root, TreeNode candidate, int k) {
        if (root == null || candidate == null) return false;
        // if can find another in the root
        if (search(root, candidate, k - candidate.val)) {
            return true;
        }
        else {
            // recur choose other candidates
            return recur(root, candidate.left, k) || recur(root, candidate.right, k);
        }
    }
    // search the val in the whole tree
    public boolean search(TreeNode root, TreeNode candidate, int val) {
        if (root == null || candidate == null) return false;
        if (val < root.val) {
            return search(root.left, candidate, val);
        }
        else if (val > root.val) {
            return search(root.right, candidate, val);
        }
        else {
            if (root != candidate) return true;
        }
        return false;
    }

    // Sol 2: using hashset

}
