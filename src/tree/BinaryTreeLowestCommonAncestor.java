package tree;

/**
 * Created by yingtan on 9/1/15.
 */
public class BinaryTreeLowestCommonAncestor {
    // Solution 3: buttom to up
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode leftAncestor = lowestCommonAncestor(root.left, p, q);
        TreeNode rightAncestor = lowestCommonAncestor(root.right, p, q);

        if ( (root == p) || (root == q) ) {
            return root;
        }
        else if ( (leftAncestor == null) && (rightAncestor == null) ) {
            return null;
        }
        else if ( (leftAncestor != null) && (rightAncestor != null) ) {
            return root;
        }
        else if (rightAncestor != null) {
            return rightAncestor;
        }
        else if (leftAncestor != null) {
            return leftAncestor;
        }
        return null;
    }

    public static void main(String[] args) {
        BinaryTreeLowestCommonAncestor ob = new BinaryTreeLowestCommonAncestor();
        TreeNode root = new TreeNode(0);
        TreeNode l = new TreeNode(1);
        TreeNode r = new TreeNode(2);
        root.left = l;
        root.right = r;

        System.out.println(ob.lowestCommonAncestor(root, l,r).val);
    }
}
