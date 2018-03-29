package tree;

/**
 * Created by yingtan on 3/17/18.
 */
import java.util.*;
public class BinaryTreeLowestCommonAncestorWithParent {
    public TreeNode LCA_hashset(TreeNode n1 , TreeNode n2) {
        HashSet<TreeNode> set = new HashSet<>();
        while(n1 != null || n2 != null) {
            if (n1 != null) {
                if (set.contains(n1)) {
                    return n1;
                }
                else {
                    set.add(n1);
                }
                n1 = n1.parent;
            }
            if (n2 != null) {
                if (set.contains(n2)) {
                    return n2;
                }
                else {
                    set.add(n2);
                }
                n2 = n2.parent;
            }
        }
        return null;
    }
    // Sol2: better one time : o(n) , space: o(1)
    public TreeNode LCA_better(TreeNode n1 , TreeNode n2) {
        int height1 = getHeight(n1);
        int height2 = getHeight(n2);
        if (height2 > height1) {
            // n1 is the deeper one
            TreeNode tmp = n1;
            n1 = n2;
            n2 = tmp;
        }
        for (int i = 0 ; i < Math.abs(height1 - height2); i ++) {
            n1 = n1.parent;
        }
        // now, n1 and n2 are at the same level
        while(n1 != null && n2 != null) {
            if (n1 == n2) {
                // this is the parent;
                return n1;
            }
            else {
                n1 = n1.parent;
                n2 = n2.parent;
            }
        }
        return null;
    }
    public int getHeight(TreeNode n) {
        return 0;
    }

}
