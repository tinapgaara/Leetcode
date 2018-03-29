package facebook.tree;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yingtan on 12/4/17.
 *
 * 637. Average of Levels in Binary Tree
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

 Example 1:
 Input:
 3
 / \
 9  20
 /  \
 15   7
 Output: [3, 14.5, 11]
 Explanation:
 The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 */
public class AverageOfLevelInBinaryTree {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null)  {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(! queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0 ; i < size; i ++) {
                TreeNode cur = queue.poll();
                sum = sum + cur.val;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            double avg = sum / size;
            res.add(avg);
        }
        return res;
    }
}
