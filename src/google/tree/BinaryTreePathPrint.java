package google.tree;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/5/15.
 */
public class BinaryTreePathPrint {
    // Solution 1: up - buttom; when form List
    public List<List<Integer>> printPath(TreeNode root, List<List<Integer>> paths) {
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> path: paths) {
            List<Integer> copy = new ArrayList<>(path);
            copy.add(root.val);

            res.add(copy);
        }

        List<List<Integer>> newres = new ArrayList<>();
        if (root.left != null) { // add left branch
            List<List<Integer>> left = printPath(root.left, res);
            newres.addAll(left);
        }
        if (root.right != null) { // add right branch
            List<List<Integer>> right = printPath(root.right, res);
            newres.addAll(right);
        }

        if ((root.left == null) && (root.right == null)) // add cur branch
            newres.addAll(res);

        return newres;
    }

    // Solution 2: buttom-up : when form string

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;
        TreeNode node4 = new TreeNode(4);
        node3.left = node4;

        BinaryTreePathPrint ob = new BinaryTreePathPrint();
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(list);
        System.out.println(ob.printPath(node1, lists));
    }
}
