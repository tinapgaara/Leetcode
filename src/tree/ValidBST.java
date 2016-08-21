package tree;

/**
 * Created by yingtan on 9/20/15.
 */
public class ValidBST {
    // Solution 1: used range: consider initial range(use long MAX MIN)
    public boolean isValidBST_1(TreeNode root) {
        if (root  == null) {
            return true;
        }
        return recurIsValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean recurIsValidBST(TreeNode root, long low, long high) {
        if (root == null) {
            return true;
        }
        long val = (long) root.val;
        if ( (val < high) && (val > low) ){
            boolean isValidLeft = recurIsValidBST(root.left, low, val);
            if ( ! isValidLeft) return false;
            else {
                boolean isValidRight = recurIsValidBST(root.right, val, high);
                if (isValidRight) return true;
                else return false;
            }
        }
        else
            return false;
    }

    /* Solution 2: use array to save prev pointer in recursion */
    public boolean isValidBST_2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Integer[] prevVal = new Integer[1];
        Integer Val = null;
        prevVal[0] = Val;

        return visitedValidBST(root, prevVal);
    }

    public boolean visitedValidBST(TreeNode root, Integer[] prevVal) {
        if (root == null) return true;

        if (! visitedValidBST(root.left, prevVal)) {
            return false;
        }
        if ((prevVal[0] != null) && (prevVal[0].intValue() >= root.val) ) {
            return false;
        }
        prevVal[0] = new Integer(root.val);
        if (! visitedValidBST(root.right, prevVal) ) {
            return false;
        }

        return true;
    }

    // Solution 3: use member to save changed prev pointer */
    Integer prev = null;
    public boolean isValidBST_3(TreeNode root) {
        if(root == null)
            return true;

        if(!isValidBST_3(root.left))
            return false;

        if((prev != null) && (prev >= root.val))
            return false;

        prev = root.val;
        if(!isValidBST_3(root.right))
            return false;

        return true;
    }

    public static void main(String[] args) {
        ValidBST ob = new ValidBST();
        TreeNode node = new TreeNode(0);
        ob.isValidBST_1(node);
        int[][] a = new int[1][1];
        a[0][0] = 3;
        ob.change(a);
        System.out.println(a[0][0]);

    }

    public  void change(int[][] a) {
        a[0][0] = 2;
    }
}
