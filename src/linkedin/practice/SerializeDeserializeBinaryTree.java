package linkedin.practice;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 11/22/17.
 */
public class SerializeDeserializeBinaryTree {

    // Sol 1 : BFS level order
    public String seri_bfs(TreeNode root) {
        /*
        *  1
        * 2 3
        *   4 5
        *
        * 1,2,3, #,#, 4, 5,#,#,#,#
        * */
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        StringBuilder b = new StringBuilder();
        while(! queue.isEmpty()){
            TreeNode cur = queue.poll();
            if (cur != null) {
                b.append(cur.val + ",");

                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            else {
                b.append("#,");
            }
        }
        b.deleteCharAt(b.length() - 1);
        return b.toString();
    }

    // Sol 1 : BFS level order
    public TreeNode de_bfs(String s) {
        String[] parts = s.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while(! queue.isEmpty() && i < s.length()) {
            TreeNode cur = queue.poll();
            String leftStr = parts[i];
            if (! leftStr.equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(leftStr));
                cur.left = left;
                queue.offer(left);
            }
            i ++;
            String rightStr = parts[i];
            if (! rightStr.equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(rightStr));
                cur.right = right;
                queue.offer(right);
            }
            i ++;
        }
        return root;
    }

    // Sol 2 : DFS recursion order
    public String seri_dfs_recur(TreeNode root) {
        // preorder
        if (root == null) return "#";
        StringBuilder b = new StringBuilder();
        b.append(root.val + ",");
        b.append(seri_dfs_recur(root.left) + "," + seri_dfs_recur(root.right));
        return b.toString();
    }

    // Sol 2 : DFS recursion order
    public TreeNode de_dfs_recur(String s) {
        String[] parts = s.split(",");
        int[] index = new int[1];
        index[0] = 0;
        return dfs_recur(parts, index);
    }

    public TreeNode dfs_recur(String[] parts, int[] index) {
        int i = index[0];
        if (parts[i].equals("#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(parts[i]));
        index[0] ++;
        TreeNode left = dfs_recur(parts, index);
        index[0] ++;
        TreeNode right = dfs_recur(parts, index);

        root.left = left;
        root.right = right;
        return root;
    }

    // Sol 3 : DFS iterative order
    /*
    public String seri_dfs_iter(TreeNode root) {

    }

    // Sol 3 : DFS iterative order
    public TreeNode de_dfs_iter(String s) {

    }
    */
}
