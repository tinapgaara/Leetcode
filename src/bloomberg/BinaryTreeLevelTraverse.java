package bloomberg;

import stack.TrapRainWater;
import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by max2 on 10/16/15.
 */
public class BinaryTreeLevelTraverse {

    public void levelTraverse(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(node);
        Queue<Integer> level = new LinkedList<Integer>();
        level.offer(1);

        int prevLevel = 1;
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            Integer curLevel = level.poll();
            if (curLevel == prevLevel + 1) {
                // do sth when change level
            }

            if ((cur != null) && (curLevel != null)) {
                queue.offer(cur.left);
                queue.offer(cur.right);
                level.offer(curLevel + 1);
                level.offer(curLevel + 1);
            }
        }
    }
}
