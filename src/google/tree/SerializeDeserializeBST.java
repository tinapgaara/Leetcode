package google.tree;

import jdk.internal.util.xml.impl.Pair;
import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 2/26/17.
 *
 * 449. Serialize and Deserialize BST Add to List
 Description  Submission  Solutions
 Total Accepted: 7679
 Total Submissions: 18619
 Difficulty: Medium
 Contributors: ben65
 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
 */
public class SerializeDeserializeBST {

    // Encodes a tree to a single string.
    // preorder
    public String serialize(TreeNode root) {
        if (root == null) return "#";

        String left = serialize(root.left);
        String right = serialize(root.right);

        return root.val + "," + left + "," + right;

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] parts = data.split(",");
        Queue<String> queue = new LinkedList<String>();
        for (int i = 0 ; i < parts.length; i ++) {
            queue.offer(parts[i]);
        }
        return buildTree(queue);
    }

    public TreeNode buildTree(Queue<String> queue) {
        String first = queue.remove();
        if (first.equals("#")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(first));
        node.left = buildTree(queue);
        node.right = buildTree(queue);
        return node;
    }
}
