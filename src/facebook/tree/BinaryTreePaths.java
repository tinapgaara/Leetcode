package facebook.tree;
import tree.TreeNode;

import java.util.*;
// leetcode: 257 binary tree path
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        recurFind(root, "", res);
        return res;
    }
    public void recurFind(TreeNode cur, String path, List<String> res) {
        path = path + cur.val;
       if (cur.left == null && cur.right == null) {
            res.add(path);
            // If need to print: print res here, still need memory to store paths in printing
            return;
        }
        else if (cur.left != null && cur.right != null) {
            recurFind(cur.left, path + "->", res);
            recurFind(cur.right, path + "->", res);
        }
        else if (cur.left != null) {
            // right is null
            recurFind(cur.left, path + "->", res);
        }
        else {
            // left is null
            recurFind(cur.right, path + "->", res);
        }
    }
    // follow up: given you a tree with left child and right child, but is a graph and be connected: can have cycle
    public List<String> binaryTreePaths_cycle(TreeNode root) {
        List<String> res = new ArrayList<>();
        Set<TreeNode> vis = new HashSet<>();
        recurFind_cycle(root, "", vis, res);
        return res;
    }
    public void recurFind_cycle(TreeNode cur, String path, Set<TreeNode> vis, List<String> res) {
        path = path + cur.val;
        if (cur.left == null && cur.right == null) {
            //  leaf and not visited, we need to add to result
            res.add(path);
            return;
        }
        else if (cur.left != null && cur.right != null) {
            if (! vis.contains(cur.left)) {
                vis.add(cur.left);
                recurFind_cycle(cur.left, path + "->", vis, res);
                vis.remove(cur.left);
            }
            if (! vis.contains(cur.right)) {
                vis.add(cur.right);
                recurFind_cycle(cur.right, path + "->", vis, res);
                vis.remove(cur.right);
            }
        }
        else if (cur.left != null) {
            if (! vis.contains(cur.left)) {
                vis.add(cur.left);
                recurFind_cycle(cur.left, path + "->", vis, res);
                vis.remove(cur.left);
            }
        }
        else {
            if (! vis.contains(cur.right)) {
                vis.add(cur.right);
                recurFind_cycle(cur.right, path + "->", vis, res);
                vis.remove(cur.right);
            }
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        root.left = t2;
        root.right = t3;
        t2.left = t5;
        t2.right = t4;
        t3.left = t4;
        t5.right = t6;
        t4.left = t6;
        BinaryTreePaths ob = new BinaryTreePaths();
        System.out.println(ob.binaryTreePaths_cycle(root));
    }
}
