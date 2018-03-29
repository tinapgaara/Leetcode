package facebook.tree;

/**
 * Created by yingtan on 2/11/18.
 *
 * 314. Binary Tree Vertical Order BinaryTreeVerticalOrderTraversal
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

 If two nodes are in the same row and column, the order should be from left to right.

 Examples:

 Given binary tree [3,9,20,null,null,15,7],
 3
 /\
 /  \
 9  20
 /\
 /  \
 15   7
 return its vertical order traversal as:
 [
 [9],
 [3,15],
 [20],
 [7]
 ]
 Given binary tree [3,9,8,4,0,1,7],
 3
 /\
 /  \
 9   8
 /\  /\
 /  \/  \
 4  01   7
 return its vertical order traversal as:
 [
 [4],
 [9],
 [3,0,1],
 [8],
 [7]
 ]
 Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
 3
 /\
 /  \
 9   8
 /\  /\
 /  \/  \
 4  01   7
 /\
 /  \
 5   2
 return its vertical order traversal as:
 [
 [4],
 [9,5],
 [3,0,1],
 [8,2],
 [7]
 ]

 */
import tree.TreeNode;

import java.util.*;
public class BinaryTreeVerticalOrderTraversal {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        // can not use dfs here,because for the same vertical node, required to output upper lever firstly, so use bfs instead
        //dfs(root, 0, map);
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> verticalIndex = new LinkedList<>();
        queue.offer(root);
        verticalIndex.offer(0);
        while(! queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int index = verticalIndex.poll();
            if (map.containsKey(index)) {
                map.get(index).add(cur.val);
            }
            else {
                List<Integer> list = new ArrayList<>();
                list.add(cur.val);
                map.put(index, list);
            }
            if (cur.left != null) {
                queue.offer(root.left);
                verticalIndex.offer(index - 1);
            }
            if (cur.right != null) {
                queue.offer(root.right);
                verticalIndex.offer(index + 1);
            }
        }
        for (List<Integer> list : map.values()) {
            res.add(list);
        }
        return res;
    }

    public void dfs(TreeNode cur, int index, TreeMap<Integer, List<Integer>> map) {
        if (cur == null) return;
        if (map.containsKey(index)) {
            map.get(index).add(cur.val);
        }
        else {
            List<Integer> list = new ArrayList<>();
            list.add(cur.val);
            map.put(index, list);
        }
        dfs(cur.left, index - 1, map);
        dfs(cur.right, index + 1, map);
    }
}
