package linkedin.practice;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yingtan on 11/22/17.
 */
public class FindLargestValueInEachTreeRow {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(! queue.isEmpty()) {
            int size = queue.size();
            int maxLevel = 0;
            for (int i = 0 ; i < size; i ++) {
                TreeNode cur = queue.poll();
                maxLevel = Math.max(maxLevel, cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(maxLevel);
        }
        return res;
    }
}
