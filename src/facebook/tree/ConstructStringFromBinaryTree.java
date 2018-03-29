package facebook.tree;

import tree.TreeNode;

public class ConstructStringFromBinaryTree {
    public String tree2str(TreeNode t) {
        if (t == null) return "";
        if (t.left == null && t.right == null) {
            return t.val + "";
        }
        else if (t.left == null) {
            String right = tree2str(t.right);
            return t.val + "()" + "(" + right + ")";
        }
        else if (t.right == null) {
            String left = tree2str(t.left);
            return t.val + "(" + left + ")";
        }
        else {
            String left = tree2str(t.left);
            String right = tree2str(t.right);
            return t.val + "(" + left + ")" + "(" + right + ")";
        }
    }
}
