package google.tree;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/5/15.
 */
/*
* Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
*
* */
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

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;

        String value = root.val + "";
        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);

        if ((left.size() > 0) || (right.size() > 0)) { // Important !!!! use || here
            value = value + "->";
            for (String str: left) {
                res.add(value + str);
            }
            for (String str : right) {
                res.add(value + str);
            }
        }
        else {
            res.add(value);
        }
        return res;
    }


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
