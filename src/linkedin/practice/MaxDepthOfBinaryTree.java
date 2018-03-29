package linkedin.practice;

import tree.TreeNode;

/**
 * Created by yingtan on 11/22/17.
 */
public class MaxDepthOfBinaryTree {

    public int depth(TreeNode cur) {
        if (cur == null) return 0;
        return Math.max(depth(cur.left), depth(cur.right)) + 1;
    }
}
