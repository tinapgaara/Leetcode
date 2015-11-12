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
        TreeNode parentCopy = null;
        while (cur != null) {
            parentCopy = cur;
            TreeNode rightCopy = cur.right;
            TreeNode leftCopy = cur.left;

            cur.left = parentRight;
            cur.right = parent;

            cur = leftCopy;
            parent = parentCopy;

            parentRight = rightCopy;
        }
        return parentCopy;
    }

    public static void main(String[] args) {
        UpsideDownTree ob = new UpsideDownTree();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n1.left = n2;

        ob.upsideDownBinaryTree(n1);
    }
}
