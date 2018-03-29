package DP;

/**
 * Created by yingtan on 1/13/18.
 *
 * 486. Predict the Winner
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

 Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

 Example 1:
 Input: [1, 5, 2]
 Output: False
 Explanation: Initially, player 1 can choose between 1 and 2.
 If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).
 So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
 Hence, player 1 will never be the winner and you need to return False.
 Example 2:
 Input: [1, 5, 233, 7]
 Output: True
 Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
 Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 */
public class PredictTheWinner {
    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length == 0) return true;
        // dp[i][j] : difference bewteen the first player and the second player, This is unrelated to player 1 or 2
        // score1 - score2
        int[][] dp = new int[nums.length][nums.length];
        recurPredict(nums, 0, nums.length - 1, dp);
        return dp[0][nums.length - 1] > 0;
    }
    // the difference between player x and player y, and x is the next player in logic. can be player 1/2
    public int recurPredict(int[] nums, int low, int high, int[][] dp) {
        if (low > high) {
            return 0;
        }
        if (dp[low][high] != 0 ) {
            return dp[low][high];
        }
        // very important !!!!
        // this function cals difference between player 1 and player 2
        // the difference is shown by this symbol: -
        int chooseLowDiff = nums[low]
                            - recurPredict(nums, low + 1, high, dp);// the diff between next player - cur player
        int chooseHighDiff = nums[high]
                            - recurPredict(nums, low, high -1, dp);// the diff between next player - cur player
        dp[low][high] = Math.max(chooseHighDiff, chooseLowDiff);
        return dp[low][high];
    }
}
