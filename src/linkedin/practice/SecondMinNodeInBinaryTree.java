package linkedin.practice;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 11/22/17.
 */
public class SecondMinNodeInBinaryTree {

    public int findSecondMinimumValue(TreeNode root) {
        // loop all nodes and find second min one
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        int secondMin = Integer.MAX_VALUE;
        queue.offer(root);
        // min one is root

        boolean isSame = true;
        while(! queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.val > root.val && cur.val < secondMin) {
                secondMin = cur.val;
            }
            if (cur.val != root.val) {
                isSame = false;
            }
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        if (isSame) return -1;
        else return secondMin;
    }
}
