package google.tree;

import tree.TreeLinkNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yingtan on 11/1/15.
 */
/*
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node.
If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

What if the given tree could be any binary tree? Would your previous solution still work?

This may be not perfect binary tree.

任意形状的⼆二叉树，把同⼀一层的node连起来

*        1
       /  \
      2    3
     / \    \
    4  5    7
*
* After calling your function, the tree should look like:
*
*        1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5 --> 7 -> NULL
*
* */
public class PopulateNextRightPointer {

    // Solution 1: use two queue and one List<TreeLinkNode>
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        Queue<TreeLinkNode> nodes = new LinkedList<>();
        Queue<Integer> levels = new LinkedList<>();

        nodes.offer(root);
        levels.offer(1);

        int prevLevel = 1;
        List<TreeLinkNode> sameLevelNodes = new ArrayList<>();

        while (! nodes.isEmpty()) {
            TreeLinkNode curNode = nodes.poll();
            int curLevel =levels.poll();

            if (curLevel != prevLevel) {
                reconstruct(sameLevelNodes);
                prevLevel = curLevel;
                sameLevelNodes = new ArrayList<>();
            }
            if (curNode != null) {
                sameLevelNodes.add(curNode);
                nodes.offer(curNode.left);
                nodes.offer(curNode.right);
                levels.offer(curLevel + 1);
                levels.offer(curLevel + 1);
            }
        }
    }

    // Solution 2: decrease space and time, when iterate the tree, change the pointer at the same time
    // when parent visit his left child and right child, connect left child and right child together, then move himself to next.
    // need to save prev pointer, and nextLevelHead pointer
    public void connect_2(TreeLinkNode root) {
        TreeLinkNode prev = null;
        TreeLinkNode levelHead = null; // pay attention, need to be put outside
        while (root != null) {
            if ((root.left == null) && (root.right == null)) {
                root = root.next;
            }
            else if ((root.left != null) && (root.right == null)) {
                if (prev == null) {
                    levelHead = root.left;
                }
                else {
                    prev.next = root.left;
                }
                prev = root.left; // pay attention: need to move prev pointer
                root = root.next;
            }
            else if ((root.left == null) && (root.right != null)) {
                if (prev == null) {
                    levelHead = root.right;
                }
                else {
                    prev.next = root.right;
                }
                prev = root.right;  // pay attention: need to move prev pointer
                root = root.next;
            }
            else {
                if (prev == null) {
                    levelHead = root.left;
                }
                else {
                    prev.next = root.left;
                }
                root.left.next = root.right;
                prev = root.right;
                root = root.next;
            }

            // change to next level
            if (root == null) {
                if (levelHead != null) {
                    root = levelHead;
                    prev = null;
                    levelHead = null;
                }
            }
        }
    }

    public void reconstruct(List<TreeLinkNode> nodes) {
        if (nodes.size() == 0) return;
        TreeLinkNode curNode = nodes.get(0);
        for (int i = 1; i < nodes.size(); i ++) {
            curNode.next = nodes.get(i);
            curNode = nodes.get(i);
        }
    }

    public static void main(String[] args) {
        TreeLinkNode n1 = new TreeLinkNode(0);
        TreeLinkNode n2 = new TreeLinkNode(2);
        TreeLinkNode n3 = new TreeLinkNode(4);
        TreeLinkNode n4 = new TreeLinkNode(1);
        TreeLinkNode n5 = new TreeLinkNode(3);
        TreeLinkNode n6 = new TreeLinkNode(-1);
        TreeLinkNode n7 = new TreeLinkNode(5);
        TreeLinkNode n8 = new TreeLinkNode(1);
        TreeLinkNode n9 = new TreeLinkNode(6);
        TreeLinkNode n10 = new TreeLinkNode(8);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n3.left = n5;
        n3.right = n6;
        n4.left = n7;
        n4.right = n8;
        n5.right = n9;
        n6.right = n10;

        PopulateNextRightPointer ob = new PopulateNextRightPointer();
        ob.connect_2(n1);

    }
}
