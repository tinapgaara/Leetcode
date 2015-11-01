package vmware;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yingtan on 10/28/15.
 */
public class LevelOrder {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        Queue<Integer> levels = new LinkedList<Integer>();

        nodes.offer(root);
        levels.offer(1);
        List<Integer> list = new ArrayList<Integer>();
        int prevLevel = -1;
        while(!nodes.isEmpty()) {
            TreeNode cur = nodes.poll();
            int level = levels.poll();
            if (level != prevLevel) {
                if (list.size() > 0) res.add(list);
                list = new ArrayList<Integer>();
                prevLevel = level;
            }
            if (cur != null) {
                list.add(cur.val);
                nodes.offer(cur.left);
                levels.offer(level + 1);
                nodes.offer(cur.right);
                levels.offer(level + 1);
            }
        }
        return res;
    }
}
