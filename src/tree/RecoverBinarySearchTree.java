package tree;

/**
 * Created by yingtan on 10/10/15.
 */
/*
* Leetcode: Recover Binary Search Tree
*
*   Two elements of a binary search tree (BST) are swapped by mistake.

    Recover the tree without changing its structure.

    Note:
    A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
*
* */
public class RecoverBinarySearchTree {

    /*
    * Solution: Using inorder iteration, swap the node's value in this way: like bubble sortion
    *
    * Given: 7 3 1 11 12
    * In 1st iteration:
    * 1) 3 7 1 11 12  // swap neighbored two nodes: because 7 > 3
    * 2) 3 1 7 11 12  // swap 7 and 1: because 7 > 1
    * 3) 3 1 7 11 12  // be stable
    *  return false   // because has swapped
    *
    * In 2nd iteration:
    * 1) 1 3 7 11 12 // swap 3 and 1: because 3 > 1
    *  return false  // because has swapped
    *
    * In 3rd iteration:
    * no swap : return true
    *
    * */

    public void recoverTree(TreeNode root) {

        TreeNode[] prev = new TreeNode[1];
        int i = 0;
        boolean flag = false;
        while (!flag) {
            prev[0] = null;
            flag = recurRecoverTree(root, prev);
        }
    }

    public boolean recurRecoverTree(TreeNode root, TreeNode[] prev) { // record if swap in this iteration
        if (root == null) return true;
        boolean flagLeft = recurRecoverTree(root.left, prev);
        boolean flagCur = true;
        if (prev[0] == null) prev[0] = root;
        else {
            if (root.val < prev[0].val) {
                int tmp = root.val;
                root.val = prev[0].val;
                prev[0].val = tmp;

                flagCur = false;
            }
            prev[0] = root;
        }
        boolean flagRight = recurRecoverTree(root.right, prev);

        return flagLeft & flagRight & flagCur;
    }
}
