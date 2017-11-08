package facebook.tree;

import tree.TreeLinkNode;

/**
 * Created by yingtan on 5/28/17.
 *
 * 117. Populating Next Right Pointers in Each Node II Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 94153
 Total Submissions: 279715
 Difficulty: Medium
 Contributor: LeetCode
 Follow up for problem "Populating Next Right Pointers in Each Node".

 What if the given tree could be any binary tree? Would your previous solution still work?

 Note:

 You may only use constant extra space.
 For example,
 Given the following binary tree,
 1
 /  \
 2    3
 / \    \
 4   5    7
 After calling your function, the tree should look like:
 1 -> NULL
 /  \
 2 -> 3 -> NULL
 / \    \
 4-> 5 -> 7 -> NULL
 */
public class PopulateNextRightPointersII {

    /**
     * Definition for binary tree with next pointer.
     * public class TreeLinkNode {
     *     int val;
     *     TreeLinkNode left, right, next;
     *     TreeLinkNode(int x) { val = x; }
     * }
     */
    public class Solution {
        public void connect(TreeLinkNode root) {
            if (root == null) return;
            // move two pointers,
            // the first one: cur is moving one the first level
            // the second one: nextLevelCur is moving on the second level
            TreeLinkNode cur = root;
            // stores the head of linkTreeNode in the next level
            TreeLinkNode nextLevelHead = null;
            // stores the current linkTreeNode in the next level
            TreeLinkNode nextLevelCur = null;

            // if we need to update the head of next level
            boolean updateNextLevelHead = true;

            while (cur != null) {
                if (cur.left != null) {
                    // update head of next level
                    if (updateNextLevelHead) {
                        nextLevelHead = cur.left;
                        nextLevelCur = nextLevelHead;
                        updateNextLevelHead = false;
                    } else if (nextLevelCur != null) {
                        nextLevelCur.next = cur.left;
                        // move the next level pointer
                        nextLevelCur = nextLevelCur.next;
                    }
                }
                if (cur.right != null) {
                    // update head of next level
                    if (updateNextLevelHead) {
                        nextLevelHead = cur.right;
                        nextLevelCur = nextLevelHead;
                        updateNextLevelHead = false;
                    } else if (nextLevelCur != null) {
                        nextLevelCur.next = cur.right;
                        // move the next level pointer
                        nextLevelCur = nextLevelCur.next;
                    }
                }
                // move the current level pointer
                cur = cur.next;
                if (cur == null) {
                    cur = nextLevelHead;
                    // important !!!!! after assign nextLevelHead to cur,
                    // need to nextLevelHead=NULL to terminate the while(root != null)
                    nextLevelHead = null;
                    updateNextLevelHead = true;
                }
            }
        }
    }
}
