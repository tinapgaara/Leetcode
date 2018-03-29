package tree;

/**
 * Created by yingtan on 3/17/18.
 */
public class CountCompleteTree {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int leftHeight = leftheight(root); // o(h)
        int rightHeight = rightheight(root);
        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1;
        }
        else {
            // although call count(l) + count(r), because this is complete tree, so the count(r) must be called only once, there must be a perfect binary tree
            // so time complxity in this step is o(h)
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
    public int leftheight(TreeNode root) {
        int h = 0;
        while(root != null) {
            h ++;
            root = root.left;
        }
        return h;
    }
    public int rightheight(TreeNode root) {
        int h = 0;
        while(root != null) {
            h ++;
            root = root.right;
        }
        return h;
    }

}
