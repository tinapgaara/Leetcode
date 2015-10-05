package tree;

import apple.laf.JRSUIUtils;

/**
 * Created by yingtan on 9/1/15.
 */
public class CompleteTree {

    /* Solution 1: exceed limit
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = calLeftHeight(root);
        int rightHeight = calRightHeight(root);
        System.out.println(leftHeight);
        System.out.println(rightHeight);
        if (leftHeight == rightHeight) {
            return (int)(Math.pow(2, leftHeight) - 1);
        }
        else {
            return recurCountNodes(root);
        }
    }

    public int calLeftHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return calLeftHeight(root.left) + 1;
    }

    public int calRightHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return calRightHeight(root.right) + 1;
    }

    public int recurCountNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return recurCountNodes(root.left) + 1 + recurCountNodes(root.right);
    }
    */

    // Solution 2: exceed time limit
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        TreeNode cur = root;
        int leftHeight = 0;
        int rightHeight = 0;
        while(cur != null) {
            leftHeight ++;
            cur = cur.left;
        }
        cur = root;
        while(cur != null) {
            rightHeight ++;
            cur = cur.right;
        }

        System.out.println("l:"+leftHeight);
        System.out.println("r:"+rightHeight);
        if (leftHeight == rightHeight) {
            return (int)(Math.pow(2,leftHeight) - 1);
        }
        else {
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l = new TreeNode(2);
        TreeNode r = new TreeNode(3);
        root.left = l;
        root.right = r;
        TreeNode ll = new TreeNode(4);
        l.left = ll;
        CompleteTree ob = new CompleteTree();
        System.out.println(ob.countNodes(root));
    }
}
