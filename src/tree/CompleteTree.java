package tree;

import apple.laf.JRSUIUtils;

/**
 * Created by yingtan on 9/1/15.
 *
 * The idea to check complete binary tree is simple. We do a usual level order traversal and
 * whenever we see that there is a node which does not have both left and right child, then we set
 * nonFullNodeSeen = true. This indicates that while doing level order traversal,
 * we have seen our first node with either right or left or both children absent.
 * And for a complete binary tree, all the nodes visited during level order traversal after
 * visiting a nonFullNode would be leaf nodes. If this is not true, we return false.
 * For example, in the left-hand side complete tree shown above, once we visit node '3' which is a nonFullNode, the nodes visited after this node are node '4','5' and '6' all of which are leaf nodes.

 */
import java.util.*;
public class CompleteTree {
    // bfs, once hit a node which is a not a full node, the rest of nodes are not full nodes as well.  time : o(n) , space: o(n)
    public boolean isComplete(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isNotFull = false;
        while(! queue.isEmpty()) {
            TreeNode cur = queue.poll();
            // if we already seen a not full node before, this one must be leaf
            if (isNotFull) {
                // not a leave
                if (cur.left != null || cur.right != null) {
                    return false;
                }
            }
            // if left is null but right is not null, can't be a complete tree
            if (cur.left == null && cur.right != null) {
                return false;
            }
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            else {
                isNotFull = true;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
            else {
                isNotFull = true;
            }
        }
        return true;
    }

    public boolean isCompleteRecur(TreeNode root, int start, int n) {
        if (root == null) return true;
        if (root.left != null) {
            int leftnode = 2 * start + 1;
            if (leftnode < n) {
                return isCompleteRecur(root.left, leftnode, n);
            }
            else {
                return false;
            }
        }
        if (root.right != null) {
            int rightnode = 2 * start + 2;
            if (rightnode < n) {
                return isCompleteRecur(root.right, rightnode, n);
            }
            else {
                return false;
            }
        }
        return true;
    }

}
