package google.tree;

import tree.TreeNode;

import java.util.*;

/**
 * Created by yingtan on 2/27/17.
 * 314. Binary Tree Vertical Order Traversal
 *
 * Given binary tree [3,9,20,null,null,15,7],
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


 Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

 If two nodes are in the same row and column, the order should be from left to right.
 *
 */
public class BinaryTreeVerticalOrderTraversal {

    /*
    * Keep record of a depth, when go left node, depth ++, when go right node, depth --
    * sort from higher depth to lower depth
    *
    * When have the same depth:
    * Keep record of a recursion depth, when recur more lower, put the node in the front of list
    * case: [3,9,20,null,null,15,7]
    * expected: [[9],[3,15],[20],[7]]
    *
    * When have the same depth && recurDepth:
    * Keep record of a time, when this node is visited firstly, it should be put in the front of list
    * case: [3,9,8,4,0,1,7]
    * Expected: [4],[9],[3,0,1],[8],[7]]
    *
    *
    * */


    public List<List<Integer>> verticalOrder_simpler(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;

        Map<Integer, List<Integer>> colToNodes = new HashMap<Integer, List<Integer>>();
        // Important !!! init min and max with 0 instead of MAX_VALUE and MIN_VALUE, or [1] can not pass
        int min = 0;
        int max = 0;
        Queue<Integer> cols = new LinkedList<Integer>();
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        cols.offer(0);
        nodes.offer(root);

        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            int col = cols.poll();
            if (! colToNodes.containsKey(col)) {
                List<Integer> ns = new ArrayList<Integer>();
                colToNodes.put(col, ns);
            }
            colToNodes.get(col).add(node.val);

            if (node.left != null) {
                nodes.offer(node.left);
                cols.offer(col - 1);
                min = Math.min(min, col - 1);
            }
            if (node.right != null) {
                nodes.offer(node.right);
                cols.offer(col + 1);
                max = Math.max(max, col + 1);
            }
        }
        for (int i = min; i <= max; i++) {
            res.add(colToNodes.get(i));
        }

        return res;
        /*
        NodeComparator comparator = new NodeComparator();
        PriorityQueue<Node> queue = new PriorityQueue<Node>(comparator);
        recurVerticalOrder(root, 0, queue, 0);

        int prevDepth = Integer.MIN_VALUE;
        while (! queue.isEmpty()) {
            Node n = queue.poll();
            if (n.depth != prevDepth) {
                prevDepth = n.depth;
                List<Integer> newlist = new ArrayList<>();
                newlist.add(n.node.val);

                res.add(newlist);
            }
            else {
                List<Integer> last = res.get(res.size()-1);
                last.add(n.node.val);
            }
        }
        return res;
        */
    }

    int time = 0;
    public class Node {

        TreeNode node;
        int depth;
        int recurDepth;
        int time;

        public Node(TreeNode n, int d, int recur, int time) {
            node = n;
            depth = d;
            recurDepth = recur;
            this.time = time;
        }
    }

    public class NodeComparator implements Comparator<Node> {
        public int compare(Node n1, Node n2) {

            // higher depth, put front
            if (n1.depth > n2.depth) return -1;
            else if (n1.depth < n2.depth) return 1;

            else {
                // lower recursion depth, put front
                if (n1.recurDepth > n2.recurDepth) return 1;
                else if (n1.recurDepth < n2.recurDepth) return -1;

                else {
                    // first visit, first appear
                    // lower time, put front
                    if (n1.time > n2.time) return 1;
                    else if (n1.time < n2.time) return -1;
                }
            }
            return 0;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;

        NodeComparator comparator = new NodeComparator();
        PriorityQueue<Node> queue = new PriorityQueue<Node>(comparator);
        recurVerticalOrder(root, 0, queue, 0);

        int prevDepth = Integer.MIN_VALUE;
        while (! queue.isEmpty()) {
            Node n = queue.poll();
            if (n.depth != prevDepth) {
                prevDepth = n.depth;
                List<Integer> newlist = new ArrayList<>();
                newlist.add(n.node.val);

                res.add(newlist);
            }
            else {
                List<Integer> last = res.get(res.size()-1);
                last.add(n.node.val);
            }
        }
        return res;
    }

    public void recurVerticalOrder(TreeNode root, int depth, PriorityQueue<Node> queue, int recurDepth) {
        if (root == null) return;
        time ++;
        queue.offer(new Node(root, depth, recurDepth, time));
        recurVerticalOrder(root.left, depth + 1, queue, recurDepth + 1);
        recurVerticalOrder(root.right, depth - 1, queue, recurDepth + 1);
    }
}
