package linkedlist;

import tree.TreeLinkNode;

/**
 * Created by yingtan on 10/10/15.
 */
public class PopulatePointers {
    /*
    *
    * Leetcode:
    *
    * Populating Next Right Pointers in Each Node
    *
    *
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

    public void connect(TreeLinkNode root) {

        // Solution 1: traverse level, using complete tree properties:
        //    so many places do not need to judge the Null TreeNode
        if (root == null) return;
        if (root.left == null && root.right == null) {
            return;
        }
        while ( (root != null) && (root.left != null) ) { // because root.left != null,
            TreeLinkNode copy = root;
            root.left.next = root.right;
            while ( (root.next != null) ) { //so root.right != null
                root.right.next = root.next.left; // because root.next != null
                // and root.next and root in same level and complete tree
                // so, root.next.left and root.next.right != null
                root.next.left.next = root.next.right;

                root = root.next;
            }
            root = copy.left;
        }

        // Solution 2: use Queue: However: You may only use constant extra space.

    }


    /*
    * Follow up for problem "Populating Next Right Pointers in Each Node".

        What if the given tree could be any binary tree? Would your previous solution still work?

        Note:

        You may only use constant extra space.
    *
    * Solution :
    * levelHead:
    *                  (levelHead)
    *                  levelPointer1 levelPointer2   levelPointer3 = NULL (then, move to the nextLevelHead)
    *   null    null       3           4
    *                   /     \     /     \
    *         (nextLevelHead)
    *                 2       3    null     5
    *               prev1   cur1
    *                      prev2   cur2
    *                              prev3   cur3
    * */

    public void connect_2(TreeLinkNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            return;
        }
        TreeLinkNode levelHead = root; // record the first node in this level which is not NULL
        TreeLinkNode levelPointer = root; // tmp pointer which iterates the whole level
        TreeLinkNode nextLevelHead = null;// keep the first node in the next level which is not NULL
        while (true) { // find the first node in current's next level which is not NULL : FIND prev

            TreeLinkNode prev = null; //the prev pointer of next level's first not NULL node.
            boolean isLeft = true;//record if the next node to find is left children
            if (levelHead.left != null) {
                prev = levelHead.left;
                nextLevelHead = prev; // preserve the next level's first not null head.
                isLeft = false;
                levelPointer = levelHead;
            }
            else if (levelHead.right != null){
                prev = levelHead.right;
                nextLevelHead = prev; // preserve the next level's first not null head.
                levelPointer = levelHead.next;//next node is left children, should go next levelHead
            }
            else {
                while ((levelHead.right == null) && (levelHead.left == null)) {
                    levelHead = levelHead.next; // find the next this level's node which has children
                    // the whole level's nodes has no childern: last level, so return.
                    if (levelHead == null) return;

                }
                continue;
            }
            TreeLinkNode cur = null;
            while (levelPointer != null) { // iterate this level to build connection: FIND cur
                if (isLeft) {
                    cur = levelPointer.left;// the cur pointer of next level's second node
                    isLeft = false;
                }
                else {
                    cur = levelPointer.right;
                    isLeft = true;
                    levelPointer = levelPointer.next;
                }
                if (cur != null) { // if cur not null, then build the next pointer, prev move to cur
                    prev.next = cur;
                    prev = cur;
                }
            }
            levelHead = nextLevelHead;// pointer move the next level's first not NULL node
        }
    }
}
