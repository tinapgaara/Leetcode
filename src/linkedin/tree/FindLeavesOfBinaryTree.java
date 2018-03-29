package linkedin.tree;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/19/17.
 * 366. Find Leaves of Binary Tree
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 Example:
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
 *
 *
 */
public class FindLeavesOfBinaryTree {

    public List<List<Integer>> findLeaves(TreeNode root) {
        // [4,5,3] -> index 0, depth 0
        // [2] -> index 1, depth 1
        // [1] -> index 2, depth 2
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        depth(root,res);
        return res;
    }

    public int depth(TreeNode cur, List<List<Integer>> res) {
        if (cur == null) {
            return -1;
        }
        int curDepth = Math.max(depth(cur.left, res), depth(cur.right, res)) + 1;
        if (curDepth == res.size()) {
            List<Integer> level = new ArrayList<>();
            level.add(cur.val);
            res.add(level);
        }
        else {
            res.get(curDepth).add(cur.val);
        }
        return curDepth;
    }
}
