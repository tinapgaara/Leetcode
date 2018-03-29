package facebook.tree;

import linkedin.tree.ReshapeTree;
import tree.TreeNode;

/**
 * Created by yingtan on 2/11/18.
 *
 * 297. Serialize and Deserialize Binary Tree
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

 For example, you may serialize the following tree

 1
 / \
 2   3
 / \
 4   5
 as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 */
import java.util.*;
public class SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //return serialize_bfs(root);
        //return serialize_dfs_recursion(root);
        return serialize_dfs_iteration(root);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //return deserialize_bfs(data);
        //return deserialize_dfs_recursion(data);
        return deserialize_dfs_iteration(data);
    }

    // Solution 1: bfs
    public String serialize_bfs(TreeNode root) {
        if (root == null) return "null";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder builder = new StringBuilder();
        while(! queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                builder.append("null,");
                continue;
            }
            else {
                builder.append(cur.val + ",");
            }
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
    public TreeNode deserialize_bfs(String data) {
        if (data.equals("null")) return null;
        String[] parts = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        String rootstr = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(rootstr));
        queue.offer(root);
        int i = 1;
        while(! queue.isEmpty()) {
            TreeNode cur = queue.poll();
            String left = parts[i];
            i ++;
            String right = parts[i];
            i ++;
            if (left.equals("null")) {
                cur.left = null;
            }
            else {
                TreeNode leftNode = new TreeNode(Integer.parseInt(left));
                cur.left = leftNode;
                queue.offer(leftNode);
            }
            if (right.equals("null")) {
                cur.right = null;
            }
            else {
                TreeNode rightNode = new TreeNode(Integer.parseInt(right));
                cur.right = rightNode;
                queue.offer(rightNode);
            }
        }
        return root;
    }
    // Solution 2: dfs_recursion
    public String serialize_dfs_recursion(TreeNode root) {
        if (root == null) return "null";
        String cur = root.val + ",";
        String left = serialize_dfs_recursion(root.left);
        String right = serialize_dfs_recursion(root.right);
        return cur + left + "," + right;
    }
    public TreeNode deserialize_dfs_recursion(String data) {
        String[] parts = data.split(",");
        int[] index = new int[1];
        index[0] = 0;
        return deserialize_dfs_recursion_recur(parts, index);
    }
    public TreeNode deserialize_dfs_recursion_recur(String[] parts, int[] index) {
        String cur = parts[index[0]];
        if (cur.equals("null")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(cur));
        index[0] ++;
        node.left = deserialize_dfs_recursion_recur(parts, index);
        index[0] ++;
        node.right = deserialize_dfs_recursion_recur(parts, index);
        return node;
    }
    // Solution 3: dfs_iteration: stack
    public String serialize_dfs_iteration(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return "null";
        }
        stack.push(root);
        StringBuilder builder = new StringBuilder();
        while(! stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur == null) {
                builder.append("null,");
                continue;
            }
            builder.append(cur.val + ",");
            stack.push(cur.right);
            stack.push(cur.left);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
    // iteration do preorder: very important !!!!
    public TreeNode deserialize_dfs_iteration(String data) {
        String[] parts = data.split(",");
        String curstr = parts[0];
        if (curstr.equals("null")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(curstr));
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(cur);
        System.out.println("push:" + cur.val);
        int i = 1;
        int len = parts.length;
        while(i < len) {
            while(i < len && ! parts[i].equals("null")) {
                // keep pushing left
                TreeNode left = new TreeNode(Integer.parseInt(parts[i]));
                i ++;
                cur.left = left;
                cur = cur.left;
                stack.push(cur);
            }
            while(i < len && parts[i].equals("null")) {
                if (! stack.isEmpty()) { // important !!!!
                    cur = stack.pop();
                }
                i ++;
            }
            if (i < len) {
                // right node
                TreeNode right = new TreeNode(Integer.parseInt(parts[i]));
                i ++;
                cur.right = right;
                cur = cur.right;
                stack.push(cur);
            }
        }
        return root;
    }
}
