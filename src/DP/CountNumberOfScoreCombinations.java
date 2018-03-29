package DP;

/**
 * Created by yingtan on 1/13/18.
 */
import java.util.*;
public class CountNumberOfScoreCombinations {
    // time: o(playerNumber * score)
    // space: o(playerNumber * score)
    /*
    * In this case,
    * [1, 2, 3] -> target = 4
    * combinations number: 4
    * one 1, one 3
    * two 2
    * three 1, one 2
    * four 1
    *
    * */
    public int countCombinations(List<Integer> individualPlayScores, int targetScore) {
        int[][] dp = new int[individualPlayScores.size()][targetScore + 1];
        for (int i = 0 ; i < individualPlayScores.size(); i ++) {
            dp[i][0] = 1;
            for (int j = 1 ; j<= targetScore; j ++) {
                // dp[i][j] =
                // 1. dp[i-1][j] without the play i
                // 2. dp[i][j - playIScore] with the play i
                int withoutPlayI = 0;
                if (i-1 >= 0) {
                    withoutPlayI = dp[i-1][j];
                }
                int withPlayI = 0;
                if (j - individualPlayScores.get(i) >= 0) {
                    withPlayI = dp[i][j - individualPlayScores.get(i)];
                }
                dp[i][j] = withoutPlayI + withPlayI;
            }
        }
        for (int i = 0 ; i < dp.length; i ++) {
            for (int j = 0 ; j < dp[0].length; j ++) {
                System.out.print(" " + dp[i][j]);
            }
            System.out.println();
        }
        return dp[individualPlayScores.size() - 1][targetScore];
    }

    // better space solution:
    /*
    * Since
    * dp[i][j] = dp[i-1][j] + dp[i][j - playIScore]
    * when we cal result for dp[i][j], we already get the result of dp[i][j - playIScore], and dp[i-1][j] is also calcualted
    * so, we can decrease the space:
    * dp[j]_new = dp[j]_old + dp[j - playIScore]
    * when we calculate a new dp[j], we still have the old dp[j], and we already calculated dp[j-playIScore] and keep this at a different index
    *
    * pattern:
    * dp[i][x] = dp[i-1][x] + dp[i][y], // y < x; can decrease space to:
    * dp[x] = dp[x] + dp[y]
    * */
    public int countCombinations_better(List<Integer> individualPlayScores, int targetScore) {
        int[] dp = new int[targetScore + 1];
        for (int i = 0; i < individualPlayScores.size(); i ++) {
            dp[0] = 1;
            for (int j = 1; j <= targetScore; j ++) {
                if (j - individualPlayScores.get(i) >= 0) {
                    dp[j] = dp[j] + dp[j - individualPlayScores.get(i)]; // dp[i-1][j] + dp[i][j - playIScore]
                }
            }
        }
        return dp[targetScore];
    }

    public static void main(String[] args) {
        CountNumberOfScoreCombinations ob = new CountNumberOfScoreCombinations();
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        System.out.println(ob.countCombinations(nums, 4));
    }
}
