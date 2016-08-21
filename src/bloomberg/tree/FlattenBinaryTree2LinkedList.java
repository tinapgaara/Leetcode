package bloomberg.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 11/15/15.
 */
/*
*Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
*
* */
public class FlattenBinaryTree2LinkedList {

    public void flatten(TreeNode root) {
        if (root == null) return;

        TreeNode right = root.right;
        TreeNode left = root.left;

        flatten(left);

        root.right = left;
        root.left = null;

        while ((root != null) && (root.right != null)) {
            root = root.right;
        }

        root.right = right;

        flatten(right);
    }

    public static void main(String[] args) {
        FlattenBinaryTree2LinkedList ob = new FlattenBinaryTree2LinkedList();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n1.left = n2;
        TreeNode n3 = new TreeNode(3);
        n1.right = n3;
        TreeNode n4 = new TreeNode(4);
        n2.left = n4;
        TreeNode n5 = new TreeNode(5);
        n2.right = n5;

        ob.flatten(n1);
        System.out.println();
    }
}
