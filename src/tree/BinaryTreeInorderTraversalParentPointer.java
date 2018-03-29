package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 12/7/17.
 */
public class BinaryTreeInorderTraversalParentPointer {

    public class TreeNodeParent {
        TreeNodeParent left;
        TreeNodeParent right;
        TreeNodeParent parent;
        int val;
        public TreeNodeParent(int val) {
            this.val = val;
        }
    }
    // o(1) space, o(n) time
    public List<Integer> inorderTraversal(TreeNodeParent root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        TreeNodeParent cur = root;
        TreeNodeParent prev = null;
        TreeNodeParent next = null;
        while(cur != null) {
            // have not visited cur
            // we came down to cur from prev
            /*
            *   node <- prev
            *    /
            *  node <- cur
            *
            *  or
            *
            *  node <- prev
            *   \
            *   node <- cur
            *
            * */
            if (cur.parent == prev) {
                if (cur.left == null) {
                    res.add(cur.val);
                    if (cur.right == null) {
                        next = prev;
                    }
                    else {
                        next = cur.right;
                    }
                }
                else {
                    next = cur.left;
                }
            }
            // already visited cur, back to cur
            // we came up to cur from its left child
            /*
            *   node <- cur
            *    /
            *  node <- prev
            * */
            else if (cur.left ==  prev) {
                if (cur.right == null) {
                    res.add(cur.val);
                    next = cur.parent;
                }
                else {
                    next = cur.right;
                }
            }
            else {
                /*
                * node <- cur
                *   \
                *   node <- prev
                * */
                // done with both children, move up
                next = cur.parent;
            }

            // move
            prev = cur;
            cur = next;
        }
        return res;
    }
}
