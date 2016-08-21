package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 12/14/15.
 */
/*
*Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

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
* */
public class BinaryTreePathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();

        recurPathSum(root, sum, res, path);
        return res;
    }

    public void recurPathSum(TreeNode root, int sum, List<List<Integer>> res, List<Integer> path) {
        if (root == null) {
            return;
        }
        else if ((root.left == null) && (root.right == null)) {
            if (root.val == sum) {
                List<Integer> newlist = new ArrayList<Integer>(path);
                newlist.add(sum);
                res.add(newlist);
            }
            return;
        }
        path.add(root.val);
        recurPathSum(root.left, sum - root.val, res, path);
        recurPathSum(root.right, sum - root.val, res, path);
        path.remove(path.size()-1);
    }
}

