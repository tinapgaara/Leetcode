package tree;

/**
 * Created by max2 on 10/9/15.
 */
public class FlattenBinaryTree2LinkedList {

    public void flatten(TreeNode root) {
        if ( root == null) return;

        TreeNode right = root.right;
        TreeNode left = root.left;
        TreeNode copyLeft = left;
        flatten(left);
        while ((left != null) && (left.right != null)) {
            left = left.right;
        }
        if (left != null) {
            left.right = right;
            root.right = copyLeft;
            root.left = null;
        }
        flatten(right);

        return;
    }
}
