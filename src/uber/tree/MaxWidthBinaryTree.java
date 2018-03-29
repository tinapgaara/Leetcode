package uber.tree;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 10/8/17.
 *
 * 662. Maximum Width of Binary Tree
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

 The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

 Example 1:
 Input:

 1
 /   \
 3     2
 / \     \
 5   3     9

 Output: 4
 Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 Example 2:
 Input:

 1
 /
 3
 / \
 5   3

 Output: 2
 Explanation: The maximum width existing in the third level with the length 2 (5,3).
 Example 3:
 Input:

 1
 / \
 3   2
 /
 5

 Output: 2
 Explanation: The maximum width existing in the second level with the length 2 (3,2).
 Example 4:
 Input:

 1
 / \
 3   2
 /     \
 5       9
 /         \
 6           7
 Output: 8
 Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


 下面这个解法用的是层序遍历，迭代的方法来写的，注意这里使用了队列queue来辅助运算，
 queue里存的是一个pair，结点和其当前位置，在进入新一层的循环时，首先将首结点的位置保存出来当作最左位置，
 然后对于遍历到的结点，都更新右结点的位置，遍历一层的结点后来计算宽度更新结果res，参见代码如下
 */
public class MaxWidthBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {
        if(root == null ) return 0;
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(new Node(root, 0));
        int max = 0;
        while(! queue.isEmpty()) {
            int leftmost = queue.peek().index;
            int rightmost = leftmost;
            int size = queue.size(); // important !!!
            for (int i = 0 ; i < size; i ++) {
                Node cur = queue.poll();
                rightmost = cur.index;

                if (cur.node.left != null) {
                    queue.offer(new Node(cur.node.left, 2*rightmost + 1));
                }
                if (cur.node.right != null) {
                    queue.offer(new Node(cur.node.right, 2*rightmost + 2));
                }
            }
            max = Math.max(max, rightmost - leftmost + 1);
        }
        return max;
    }

    public class Node {
        TreeNode node;
        int index;

        public Node(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}
