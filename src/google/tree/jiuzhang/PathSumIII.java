package google.tree.jiuzhang;

import tree.TreeNode;

/**
 * Created by yingtan on 10/29/17.
 *
 * 437. Path Sum III
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 You are given a binary tree in which each node contains an integer value.

 Find the number of paths that sum to a given value.

 The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

 The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

 Example:

 root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

 10
 /  \
 5   -3
 / \    \
 3   2   11
 / \   \
 3  -2   1

 Return 3. The paths that sum to 8 are:

 1.  5 -> 3
 2.  5 -> 2 -> 1
 3. -3 -> 11
 */
public class PathSumIII {

    /*
    Space: O(n) due to recursion.
    Time: O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree).
    */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    public int pathSumFrom(TreeNode root, int sum) {
        if (root == null) return 0;
        int count = 0;
        if (root.val == sum) {
            count ++;
            // important !! can not return here
            //return count;
        }
        count = count + pathSumFrom(root.left, sum - root.val);
        count = count + pathSumFrom(root.right, sum - root.val);
        return count;
    }
}
