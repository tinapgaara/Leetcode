package google.tree;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yingtan on 2/27/17.
 * 102. Binary Tree Level Order Traversal
 *
 * For example:
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
 *
 */
public class BinaryTreeLevelOrderTraversal {

    public class Node {
        TreeNode node;
        int depth;

        public Node(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (res == null) return res;

        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(new Node(root, 0));
        int prevlevel = -1;

        while (! queue.isEmpty()) {
            Node node = queue.poll();
            TreeNode treeNode = node.node;
            int depth = node.depth;
            if (treeNode != null) {
                if (depth != prevlevel) {
                    prevlevel = depth;
                    List<Integer> newlist = new ArrayList<Integer>();
                    newlist.add(treeNode.val);
                    res.add(newlist);
                }
                else {
                    List<Integer> last = res.get(res.size()-1);
                    last.add(treeNode.val);
                }
                queue.offer(new Node(treeNode.left, node.depth + 1));
                queue.offer(new Node(treeNode.right, node.depth + 1));
            }

        }
        return res;

    }


}
