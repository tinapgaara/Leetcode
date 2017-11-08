package google.tree.jiuzhang;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yingtan on 10/29/17.
 *
 * 102. Binary Tree Level Order Traversal
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its level order traversal as:
 [
 [3],
 [9,20],
 [15,7]
 ]
 */
public class BinaryTreeLevelOrder {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (res == null) return res;
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (! queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0 ; i < size; i ++) {
                TreeNode cur = queue.poll();
                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);
                list.add(cur.val);
            }
            res.add(list);
        }
        return res;
    }
}
