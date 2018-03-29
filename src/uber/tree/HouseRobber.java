package uber.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 12/5/17.
 *
 * 337. House Robber III
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

 Determine the maximum amount of money the thief can rob tonight without alerting the police.

 Example 1:
 3
 / \
 2   3
 \   \
 3   1
 Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 Example 2:
 3
 / \
 4   5
 / \   \
 1   3   1
 Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class HouseRobber {

    public int rob(TreeNode root) {
        if (root == null) return 0;
        int[] res = recurRob(root);
        return Math.max(res[0], res[1]);
    }

    public int[] recurRob(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] left = recurRob(root.left);
        int[] right = recurRob(root.right);

        int robRoot = root.val + left[1] + right[1];
        int robWithoutRoot = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{robRoot, robWithoutRoot};
    }
}
