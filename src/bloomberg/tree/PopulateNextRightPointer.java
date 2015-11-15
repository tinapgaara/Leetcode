package bloomberg.tree;

import tree.TreeLinkNode;

/**
 * Created by yingtan on 11/14/15.
 */
/*
* Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
* */
public class PopulateNextRightPointer {

    public void connect(TreeLinkNode root) {

        if (root == null) return;
        if (root.left == null && root.right == null) {
            return;
        }

        while ((root != null) && (root.left != null)){
            TreeLinkNode thisLevelHead = root.left;

            root.left.next = root.right;

            while (root.next != null) { // go through one level
                root.right.next = root.next.left;
                root.next.left.next = root.next.right;

                root = root.next;
            }

            root = thisLevelHead.left; // go to next level
        }

    }
}
