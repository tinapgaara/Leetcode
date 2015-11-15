package bloomberg.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 10/27/15.
 */
public class UpsideDownTree {

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode cur = root;
        TreeNode parent = null;
        TreeNode parentRight = null;
        TreeNode res = null;
        while (cur != null) {
            TreeNode rightCopy = cur.right;
            TreeNode leftCopy = cur.left;

            cur.left = parentRight;
            cur.right = parent;

            parent = cur;
            cur = leftCopy;

            res = parent;

            parentRight = rightCopy;
        }
        return res;
    }

    public static void main(String[] args) {
        UpsideDownTree ob = new UpsideDownTree();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n1.left = n2;
        TreeNode n3 = new TreeNode(3);
        n1.right = n3;
        TreeNode n4 = new TreeNode(4);
        n2.left = n4;
        TreeNode n5 = new TreeNode(5);
        n2.right = n5;

        ob.upsideDownBinaryTree(n1);
    }
}
