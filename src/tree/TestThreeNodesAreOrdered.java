package tree;

/**
 * Created by yingtan on 12/10/17.
 */
public class TestThreeNodesAreOrdered {
    public boolean isOrdered(TreeNode possibleAncestor, TreeNode possibleDesc, TreeNode middle) {
        TreeNode t1 = possibleAncestor;
        TreeNode t2 = possibleDesc;
        // performs interleaved searching from possibleDesc and possibleDesc to middle
        while( (t1 != middle && t2 != middle) && (t1 != null || t2 != null) && (t1 != possibleDesc && t2 != possibleAncestor)) {
            if (t1 != null) {
                if (t1.val < middle.val) {
                    t1 = t1.right;
                }
                else {
                    t1 = t1.left;
                }
            }
            if (t2 != null) {
                if (t2.val < middle.val) {
                    t2 = t2.right;
                }
                else {
                    t2 = t2.left;
                }
            }
        }

        // one node reach the middle or null or unexpected node
        // if both searches are unsuccessfully, or we got from possibleAncestor to possibleDesc without meetting middle, or
        // from possibleDesc to possibleAncestor without meeting middle, middle can not lie between possibleAncestor and possibleDesc
        if (t1 == possibleDesc || t2 == possibleAncestor || (t1 != middle && t2 != middle)) {
            return false;
        }
        if (t1 == middle) {
            // check if possibleDesc can reach middle
            return canReach(possibleDesc, middle);
        }
        else {
            return canReach(possibleAncestor, middle);
        }

    }

    public boolean canReach(TreeNode cur, TreeNode middle) {
        while (cur != null && cur != middle) {
            if (cur.val > middle.val) {
                cur = cur.right;
            }
            else {
                cur = cur.left;
            }
        }
        return cur == middle;
    }
}
