package tree;

/**
 * Created by yingtan on 9/27/15.
 */
public class CountUniValueSubTree {

    public int countUnivalSubtrees(TreeNode root) {
        Integer[] res = recurCountUni(root);

        return res[1];
    }

    public Integer[] recurCountUni(TreeNode root) {
        if (root == null) {
            Integer[] res = new Integer[3];
            res[0] = 1; // true
            res[1] = 0; // count = 0
            res[2] = null; // value
            return res;
        }
        Integer[] resLeft = recurCountUni(root.left);
        Integer[] resRight = recurCountUni(root.right);

        Integer[] result = new Integer[3];
        if ( (resLeft[0] == 1) && (resRight[0] == 1) ) {
            if ((resLeft[2] == null) && (resRight[2] == null)) {
                result[0] = 1;
                result[1] = 1;
                result[2] = root.val;
            }
            else if ((resLeft[2] != null) && (resRight[2] == null)) {
                if (root.val == resLeft[2].intValue()) {
                    result[0] = 1;
                    result[1] = resLeft[1] + 1;
                }
                else {
                    result[0] = 0;
                    result[1] = resLeft[1];
                }
                result[2] = root.val;
            }
            else if ((resRight[2] != null) && (resLeft[2] == null)) {
                if (root.val == resRight[2].intValue()) {
                    result[0] = 1;
                    result[1] = resRight[1] + 1;
                }
                else {
                    result[0] = 0;
                    result[1] = resRight[1];
                }
                result[2] = root.val;
            }
            else {
                if ((root.val == resRight[2].intValue()) && (root.val == resLeft[2].intValue())) {
                    result[0] = 1;
                    result[1] = resRight[1] + resLeft[1] + 1;
                }
                else {
                    result[0] = 0;
                    result[1] = resRight[1] + resLeft[1];
                }
                result[2] = root.val;
            }
        }
        else {
            result[0] = 0;
            result[1] = resRight[1] + resLeft[1];
            result[2] = root.val;
        }
        return result;
    }
}
