package facebook.tree;
import tree.TreeNode;

import java.util.*;
public class BinarySearchTreeToDoubleLinkedList {
    // inorder
    // node.right == node.next
    // node.left == node.prev
    public TreeNode convertToDoubleLinkedList(TreeNode root) {
        return inorderConvert(root);
    }
    public TreeNode inorderConvert(TreeNode cur) {
        if (cur == null) return null;
        TreeNode left = inorderConvert(cur.left);
        if (left != null) {
            while(left.right != null) {
                left = left.right; // keep going next
            }
            // reach left node end
            left.right = cur;
        }
        TreeNode right = inorderConvert(cur.right);
        if (right != null) {
            while(right.left != null) {
                right = right.left; // keep going prev
            }
            // reach right node start
            cur.right = right;
        }
        return cur;
    }
    // double linkedlist to balanced BST: depth1 - depth2 <= 1
    public TreeNode convertToBalancedBST(TreeNode head) {
        if (head == null) return null;
        // convert this to a balanced BST tree
        // root.right is root.next
        // root.left is root.prev

        // use two pointer to find out mid point: slow & fast
        TreeNode slow = head;
        TreeNode fast = head;
        TreeNode prev = null;
        while(slow != null && fast != null && fast.right != null) {
            prev = slow;
            slow = slow.right;
            fast = fast.right.right;
        }
        // then the slow is the mid point
        // use this as current root
        // slow's prev point: prev
        if (prev != null) {
            prev.right = null; // set mid left part ends with null
            slow.left = convertToBalancedBST(head);
        }
        else {
            slow.left = null;
        }
        slow.right = convertToBalancedBST(slow.right);
        return slow;
    }
    // better
    TreeNode treeToList(TreeNode root) {
        TreeNode[]  pivots= convertToDoubleLinkedList2(root);
        return pivots[0];
    }
    TreeNode[] convertToDoubleLinkedList2(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return new TreeNode[]{root, root};
        TreeNode[] l = convertToDoubleLinkedList2(root.left);
        TreeNode[] r = convertToDoubleLinkedList2(root.right);
        root.left =  l[1];
        if (l[1] != null)
            l[1].right = root;
        root.right = r[0];
        if  (r[0]  !=  null)
            r[0].left  =  root;
        return new TreeNode[]{l[0] == null?root:l[0],r[1] == null?root:r[1]};
    }
}
