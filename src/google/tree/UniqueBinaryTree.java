package google.tree;

/**
 * Created by yingtan on 11/6/15.
 */
/* generate all possible complete tree with number of nodes is N
*  ???????
* */
public class UniqueBinaryTree {

    public int numTrees(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        int sum = 0;
        for (int leftSubtree = 1; leftSubtree <= n; leftSubtree ++) {
            sum = sum + numTrees(leftSubtree - 1) * numTrees(n - leftSubtree);
        }
        return sum;
    }

    // change to dp:
    public int numTrees_dp(int n) {
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
