package DP;

/**
 * Created by yingtan on 1/15/18.
 *
 * We call decimal number a monotone if:D[i] <= D[i+1], 0 \leq i \leq |D|

 Write a program which takes positive number n on input and returns number of decimal numbers of length n that are strict monotone. Number can’t start with 0.
 Input : 2
 Output : 36
 Numbers are 12, 13, ... 19, 23
 24, ... 29, .... 89.

 Input : 3
 Output : 84

 */
public class NumberOfDecimalNumberLenK {

    /*// DP[i][j] is going to store monotone
    // numbers of length i+1 considering
    // j+1 digits (1, 2, 3, ..9)
    * */
    public int getNumMonotone(int k) {
        int[][] dp = new int[k+1][10];
        for (int i = 1; i <= k ; i ++) {
            for (int j = 1; j <= 9; j++) {
                int notUseThisNum = dp[i][j-1];
                // stricly mono D[i] < D[i+1]
                int useThisNum = dp[i-1][j-1];

                // mono D[i] <= D[i+1]
                int useThisNumMono = dp[i-1][j];
                dp[i][j] = notUseThisNum + useThisNum;
            }
        }
        return dp[k][9];
    }

}
