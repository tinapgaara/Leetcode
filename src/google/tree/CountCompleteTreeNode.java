package google.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 12/14/15.
 */
public class CountCompleteTreeNode {

    public int countNodes(TreeNode root) {
        return recurCount(root);
    }

    public int recurCount(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = countLeftDepth(root);
        int rightDepth = countRightDepth(root);
        if (leftDepth == rightDepth) {
            System.out.println(leftDepth + "," + rightDepth);
            System.out.println("hello:" + ((1 << leftDepth) - 1));
            return ((1 << leftDepth) - 1);
        }
        else {
            return recurCount(root.left) + 1 + recurCount(root.right);
        }
    }

    public int countLeftDepth(TreeNode root) {
        int depth = 0;
        TreeNode cur = root;
        while (cur != null) {
            depth ++;
            cur = cur.left;
        }
        return depth;
    }

    public int countRightDepth(TreeNode root) {
        int depth = 0;
        TreeNode cur = root;
        while (cur != null) {
            depth ++;
            cur = cur.right;
        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        CountCompleteTreeNode ob = new CountCompleteTreeNode();
        ob.countNodes(node1);
    }

}
