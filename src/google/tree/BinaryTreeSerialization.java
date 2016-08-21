package google.tree;

import tree.TreeLinkNode;
import tree.TreeNode;

/**
 * Created by yingtan on 11/6/15.
 */
public class BinaryTreeSerialization {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "#";
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    private int pos;
    public TreeNode deserialize(String data) {
        System.out.println(data);
        if(data.length() < 2) return null;
        String[] tokens = data.split(",");
        pos = 0;
        return build(tokens);
    }

    private TreeNode build(String[] tok) {
        if (tok[pos].equals("#")) {
            pos ++;
            return null;
        }
        TreeNode cur = new TreeNode(Integer.parseInt(tok[pos]));
        pos ++;
        cur.left = build(tok);
        cur.right = build(tok);

        return cur;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(0);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(3);
        TreeNode n6 = new TreeNode(-1);
        TreeNode n7 = new TreeNode(5);
        TreeNode n8 = new TreeNode(1);
        TreeNode n9 = new TreeNode(6);
        TreeNode n10 = new TreeNode(8);

        TreeNode n11 = new TreeNode(1);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n3.left = n5;
        n3.right = n6;
        n4.left = n7;
        n4.right = n8;
        n5.right = n9;
        n6.right = n10;

        BinaryTreeSerialization ob = new BinaryTreeSerialization();
        System.out.println(ob.deserialize(ob.serialize(n1)));

    }
}
