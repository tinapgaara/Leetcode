package bloomberg;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by max2 on 10/16/15.
 */
// Need to record, isFind(boolean[1]); depth(as parameter);
    // TODO: need to be tested
public class FindDepthOfNode {

    boolean[] isFind = new boolean[1];

    public int findDepth(TreeNode root, TreeNode node) {
        isFind[0] = false;
        return recurFindDepth(root, node, isFind, 0);
    }

    // Solution 1: use recursion
    public int recurFindDepth(TreeNode cur, TreeNode node, boolean[] isFind, int depth) {
        if (cur == null) return depth;

        depth = depth + 1;
        if (cur == node) return depth;
        else {
            int leftDepth = recurFindDepth(cur.left, node, isFind, depth);
            if (isFind[0]) return leftDepth;
            else {
                int rightDepth = recurFindDepth(cur.right, node, isFind, depth);
                if (isFind[0]) return rightDepth;
            }
        }
        return depth;
    }

    // Solution 2: use iteration: binary tree level traverse
    public int levelIterFindDepth(TreeNode root, TreeNode node) {
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        Queue<Integer> levels = new LinkedList<Integer>();

        nodes.offer(root);
        levels.offer(1);
        while (!nodes.isEmpty()) {
            TreeNode cur = nodes.poll();
            int level = levels.poll();

            if (cur == node) return level;
            else if (cur != null){
                nodes.offer(cur.left);
                nodes.offer(cur.right);
                levels.offer(level + 1);
                levels.offer(level + 1);
            }
        }
        return -1;// can not find
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(0);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;
        TreeNode n4 = new TreeNode(4);
        n3.left = n4;

        FindDepthOfNode ob = new FindDepthOfNode();
        System.out.println(ob.findDepth(n1, n4));
    }
}
