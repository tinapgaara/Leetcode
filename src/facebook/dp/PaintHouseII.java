package facebook.dp;

/**
 * Created by yingtan on 5/29/17.
 *
 * 265. Paint House II Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 19708
 Total Submissions: 52285
 Difficulty: Hard
 Contributor: LeetCode
 There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

 The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

 Note:
 All costs are positive integers.

 Follow up:
 Could you solve it in O(nk) runtime?
 */
public class PaintHouseII {

    /*
    * The idea is similar to the problem Paint House I, for each house and each color, the minimum cost of painting the house with that color should be the minimum cost of painting previous houses, and make sure the previous house doesn't paint with the same color.

We can use min1 and min2 to track the indices of the 1st and 2nd smallest cost till previous house, if the current color's index is same as min1, then we have to go with min2, otherwise we can safely go with min1.

The code below modifies the value of costs[][] so we don't need extra space.
    * */

    public int minCostII_better(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;

        int n = costs.length, k = costs[0].length;
        // min1 is the index of the 1st-smallest cost till previous house
        // min2 is the index of the 2nd-smallest cost till previous house
        int min1 = -1, min2 = -1;

        for (int i = 0; i < n; i++) {
            int last1 = min1, last2 = min2;
            min1 = -1; min2 = -1;

            for (int j = 0; j < k; j++) {
                if (j != last1) {
                    // current color j is different to last min1
                    costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
                } else {
                    costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
                }

                // find the indices of 1st and 2nd smallest cost of painting current house i
                if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                    min2 = min1; min1 = j;
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }

        return costs[n - 1][min1];
    }

    // solution : o(nk)
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int houses = costs.length;
        int colors = costs[0].length;
        // dp[i][j]: min cost of painting 0-ith house with color j in  ith house
        // Idea : assume the current color is j, then which color I should use in previous house to make sure min cost
        int[][] dp = new int[houses][colors];

        for (int j = 0; j < colors; j++)
            dp[0][j] = costs[0][j];

        for (int i = 1 ; i < houses; i ++) {
            // init the min cost of painting ? color in (i-1)th house
            // init the second min cost of painting ? color in (i-1)th house
            int prevMin = Integer.MAX_VALUE;
            int prevSecondMin = Integer.MAX_VALUE;
            int prevMinColor = 0;
            // calculate the min cost and second min cost of paiting ? color in (i-1)th house
            // caculate the color corresponding to the min cost of paining in (i-1)th house
            for (int j = 0 ; j < colors; j ++) {
                if (dp[i-1][j] < prevMin) {
                    prevSecondMin = prevMin;
                    prevMin = dp[i-1][j];
                    prevMinColor = j;
                }
                else if (dp[i-1][j] < prevSecondMin) {
                    prevSecondMin = dp[i-1][j];
                }
            }

            for (int j = 0; j < colors; j ++) {
                // dp[i-1][prevMinColor] + costs[i][j]
                if(prevMinColor != j) {
                    dp[i][j] = prevMin + costs[i][j];
                }
                // current j is same as prevMinColor, so if still want to use j as current color, need the previous colors to be prevSecondMinColor to make sure the sum is smaller as possible.
                else {
                    dp[i][j] = prevSecondMin + costs[i][j];
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int j = 0 ; j < colors; j ++) {
            min = Math.min(dp[houses-1][j], min);
        }
        return min;
    }

}
