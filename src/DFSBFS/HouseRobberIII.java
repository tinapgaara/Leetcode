package DFSBFS;

import tree.TreeNode;

/**
 * Created by yingtan on 8/6/16.
 */
public class HouseRobberIII {

    public int rob(TreeNode root) {
        if (root == null) return 0;

        int[] results = recurRob(root);

        return Math.max(results[0], results[1]);
    }

    public int[] recurRob(TreeNode root) {

        int[] result = new int[2];

        if (root == null) {
            result[0] = 0;
            result[1] = 0;
            return result;
        }

        int[] resLeft = recurRob(root.left);
        int[] resRight = recurRob(root.right);

        // result[0] is when root is selected, result[1] is when not.
        result[0] = resLeft[1] + resRight[1] + root.val;
        // when root is not selected, left child and right child also may be selected
        result[1]= Math.max(resLeft[1], resLeft[0]) + Math.max(resRight[1], resRight[0]);

        return result;
    }


}
