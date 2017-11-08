package google.tree;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 2/26/17.
 *
 * 113. Path Sum II
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

 For example:
 Given the below binary tree and sum = 22,
 5
 / \
 4   8
 /   / \
 11  13  4
 /  \    / \
 7    2  5   1
 return
 [
 [5,4,11,2],
 [5,8,4,5]
 ]
 */
public class PathSumII {

    // Top to down, pass each possible path to leave, when equals to sum on leaf level, organize a result and returns
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;

        List<Integer> path = new ArrayList<Integer>();
        recurPath(root, 0, sum, path, res);
        return res;
    }

    public void recurPath(TreeNode root, int curSum, int sum, List<Integer> path, List<List<Integer>> res) {
        if (root == null) return;
        List<Integer> pathCopy = new ArrayList<Integer>(path);
        pathCopy.add(root.val);
        if ( (root.left == null) && (root.right == null) ) { // a leaf
            if (root.val + curSum == sum) {
                res.add(pathCopy);
            }
            return;
        }
        else {
            recurPath(root.left, curSum + root.val, sum, pathCopy, res);
            recurPath(root.right, curSum + root.val, sum, pathCopy, res);
        }
    }
}
