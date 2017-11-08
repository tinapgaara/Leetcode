package tree;

/**
 * Created by yingtan on 10/28/15.
 */
public class BinaryTreeLongestConsequtive {

    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        int maxLeft = recurLongestConse(root.left, 1, root);
        int maxRight = recurLongestConse(root.right, 1, root);
        return Math.max(maxLeft, maxRight);
    }

    public int recurLongestConse(TreeNode root, int level, TreeNode prevNode) {
        if (root == null) {
            return level;
        }
        int maxLeft = 0;
        int maxRight = 0;
        if (root.val == prevNode.val + 1) {
            maxLeft = recurLongestConse(root.left, level+1, root);
            maxRight = recurLongestConse(root.right, level+1, root);
        }
        else {
            maxLeft = recurLongestConse(root.left, 1, root);
            maxRight = recurLongestConse(root.right, 1, root);
        }
        int max = Math.max(maxLeft, maxRight);
        return Math.max(level, max); // Important !!!
    }


    public int longestConsecutiveShorter(TreeNode root) {
        if (root == null) return 0;
        return recurLongest(root, 0, root.val - 1);
    }

    public int recurLongest(TreeNode cur, int curLen, int prevVal) {
        if (cur == null) return curLen;
        if (prevVal + 1 == cur.val) {
            // consecutive
            curLen = curLen + 1;
        }
        else {
            curLen = 1;
        }

        // Important !!
        /// 返回当前长度，左子树长度，和右子树长度中较大的那个
        //  1
        // 2  3
        //4 5
        return Math.max(curLen, Math.max(recurLongest(cur.left, curLen, cur.val), recurLongest(cur.right, curLen, cur.val)));
    }
}
