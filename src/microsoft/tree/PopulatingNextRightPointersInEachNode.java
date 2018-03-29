package microsoft.tree;

import tree.TreeLinkNode;

/**
 * Created by yingtan on 12/6/17.
 *
 *

 116. Populating Next Right Pointers in Each Node
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary tree

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
 */
public class PopulatingNextRightPointersInEachNode {

    // simpler solution
    public void connect_better(TreeLinkNode root) {
        TreeLinkNode leftStart = root;
        while (leftStart != null && leftStart.left != null) {
            connectThisLevel(leftStart);
            leftStart = leftStart.left;
        }
    }
    public void connectThisLevel(TreeLinkNode cur) {
        while(cur != null) {
            cur.left.next = cur.right;
            if (cur.next != null) {
                cur.right.next = cur.next.left;
            }
            cur = cur.next;
        }
    }

    public void connect(TreeLinkNode root) {
        TreeLinkNode cur = root;
        TreeLinkNode prev = null;
        TreeLinkNode nexthead = root;

        while(cur != null) {
            // important !!!!
            if (cur.left == null) break;
            if (prev == null) {
                prev = cur.left;
            }
            else {
                prev.next = cur.left;
            }
            cur.left.next = cur.right;
            prev = cur.right;
            cur = cur.next;
            if (cur == null) {
                cur = nexthead.left;
                nexthead = nexthead.left;
                prev = null;
            }
        }
    }
}
