package linkedin.practice;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/22/17.
 *
 * Example:
 Given binary tree
 1
 / \
 2   3
 / \
 4   5
 Returns [4, 5, 3], [2], [1].

 Explanation:
 1. Removing the leaves [4, 5, 3] would result in this tree:

 1
 /
 2
 2. Now removing the leaf [2] would result in this tree:

 1
 3. Now removing the leaf [1] would result in the empty tree:

 []
 Returns [4, 5, 3], [2], [1].

 time complexity: o(n)
 space: using stack
 *
 */
public class FindLeavesOfBinaryTree {

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        recurLeaves(root, res);
        return res;
    }

    public int recurLeaves(TreeNode cur, List<List<Integer>> res) {
        if (cur == null) {
             return -1;
        }
        int leftDepth = recurLeaves(cur.left, res);
        int rightDepth = recurLeaves(cur.right, res);
        int depth = Math.max(leftDepth, rightDepth) + 1;
        if (depth == res.size()) {
            List<Integer> level = new ArrayList<>();
            res.add(depth, level);
        }
        res.get(depth).add(cur.val);
        return depth;
    }
}
