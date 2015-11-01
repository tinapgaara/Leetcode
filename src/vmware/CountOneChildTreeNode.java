package vmware;

import tree.TreeNode;

/**
 * Created by yingtan on 10/28/15.
 */
/*
*    2
*  1   3
* 4
*
*
* */
public class CountOneChildTreeNode {

    // Solution 1: recursive
    public int count(TreeNode node) {
        if (node == null) return 0;

        int leftCount = count(node.left);
        int rightCount = count(node.right);

        if (((node.left == null) && (node.right != null))
                ||  ((node.right == null) && (node.left != null))) {
            return leftCount + rightCount + 1;
        }
        return leftCount + rightCount;
    }

    // Solution 2: BFS/ DFS ???

    public static void main(String[] args) {
        CountOneChildTreeNode ob = new CountOneChildTreeNode();
        TreeNode node = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(4);

        node.left = n1;
        // node.right = n2;
        n1.left = n3;

        System.out.println(ob.count(node));
    }
}
