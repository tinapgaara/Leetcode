package google.dp;

/**
 * Created by yingtan on 2/12/17.
 *
 * 375. Guess Number Higher or Lower II
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 We are playing the Guess Game. The game is as follows:

 I pick a number from 1 to n. You have to guess which number I picked.

 Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

 However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

 Example:

 n = 10, I pick 8.

 First round:  You guess 5, I tell you that it's higher. You pay $5.
 Second round: You guess 7, I tell you that it's higher. You pay $7.
 Third round:  You guess 9, I tell you that it's lower. You pay $9.

 Game over. 8 is the number I picked.

 You end up paying $5 + $7 + $9 = $21.
 */
public class GuessNumberCost {

    public int getMoneyAmount(int n) {
        // dp[i][j] cost of guess number between i and j (inclusive)
        // 当我们猜k时, 如果错了, 那么需要往左右两端继续查找, 那么最大代价即为 dp[i][j] = min(dp[i][j], X + max(dp[i][X-1], dp[X+1][j]));
        // dp[i][j] = min(k + max(dp[i][k-1] , dp[k+1][j])) // i <= k <= j

        int[][] dp = new int[n+2][n+1]; // important!  n+2 to make sure dp[k+1][end] k+1 will not exceeds array range


        for (int l = 1; l < n; l ++) {
            for (int start = 1 ; start <= n - l; start ++) {
                int end = start + l;
                dp[start][end] = Integer.MAX_VALUE; // important
                for (int k = start ; k <= end ; k ++) { // loop all possible selected elements
                    // if choose k, loss:
                    int maxLoss = Math.max(dp[start][k-1], dp[k+1][end]) + k;

                    if (maxLoss < dp[start][end]) {
                        dp[start][end] = maxLoss;
                    }
                }
            }
        }
        return dp[1][n]; // cost of guessing 1<= number <= n
    }
    public static void main(String[] args) {
        GuessNumberCost ob = new GuessNumberCost();
        System.out.println(ob.getMoneyAmount(2));
    }
}
