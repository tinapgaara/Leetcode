package tree;

/**
 * Created by yingtan on 12/9/17.
 *
 * 96. Unique Binary Search Trees
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

 For example,
 Given n = 3, there are a total of 5 unique BST's.

 1         3     3      2      1
 \       /     /      / \      \
 3     2     1      1   3      2
 /     /       \                 \
 2     1         2                 3
 */
public class UniqueBinarySearchTree {

    // calculate number of distinct BST tree
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int total = 2 ; total <= n ; total ++) {
            for (int leftsubtree = 0; leftsubtree <= total - 1; leftsubtree ++) {
                dp[total] = dp[total] + dp[leftsubtree] * dp[total - leftsubtree - 1];
            }
        }
        return dp[n];
    }
}
