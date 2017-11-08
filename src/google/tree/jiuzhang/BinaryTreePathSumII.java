package google.tree.jiuzhang;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 10/29/17.
 *
 * [LintCode] 246 Binary Tree Path Sum II 解题报告
 Description
 Your are given a binary tree in which each node contains a value. Design an algorithm to get all paths which sum to a given value. The path does not need to start or end at the root or a leaf, but it must go in a straight line down.




 Example
 Given a binary tree:

 1
 / \
 2   3
 /   /
 4   2
 for target = 6, return

 [
 [2, 4],
 [1, 3, 2]
 ]

 题目要求不一定从root出发，但是一定要从上往下。
 我们还是从root出发往下traverse。并且维护一边往下走一边把遍历到的点的值放进一个list里。
 在当前点，我们做一件事：
 把当前的list从后往前加，看能不能加到target。
 如果加到target，说明有一个解。我们把这个解放进result里。
 全部check完毕以后，我们可以继续往下走了。
 最后全部traverse结束，我们返回答案的List。
 https://yeqiuquan.blogspot.com/2017/04/lintcode-246-binary-tree-path-sum-ii.html
 */
public class BinaryTreePathSumII {

    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // Write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> list = new ArrayList<>();
        helper(root, result, list, target);
        return result;
    }

    public void helper(TreeNode root, List<List<Integer>> result, List<Integer> list, int target) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        int sum = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            sum += list.get(i);
            if (sum == target) {
                List<Integer> res = new ArrayList<>();
                for (int j = i; j < list.size(); j++) {
                    res.add(list.get(j));
                }
                result.add(res);
            }
        }
        helper(root.left, result, list, target);
        helper(root.right, result, list, target);
        list.remove(list.size() - 1);
    }
}
